package Controller;

import Model.Exception.MyException;
import Model.PrgState;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerOld {
    private IRepository repo;
    private ExecutorService executor;
    public ControllerOld(IRepository repo){
        this.repo=repo;
    }

    public void addProgram(PrgState prg){
        this.repo.add(prg);
    }
    public List<PrgState> getPrgList(){return this.repo.getPrgList();}
    /*public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        if(stk.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }*/

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
        //.filter(p -> p.isNotCompleted())
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value>heap){
        return Stream.concat(heap.stream().filter(v->v instanceof RefValue)
                        .map(v-> {
                            RefValue v1=(RefValue)v;return v1.getAddr();}),
                symTableValues.stream().filter(v->v instanceof RefValue)
                        .map(v->{RefValue v1= (RefValue)v;return v1.getAddr();})).distinct().collect(Collectors.toList());
    }


    /*public void allStep() throws MyException{
        PrgState prg = repo.getCrtPrg();
        repo. logPrgStateExec();
        try{
            while (!prg.getStk().isEmpty()){
                oneStep(prg);
                repo.logPrgStateExec();
                prg.getHeapTable().setContent(unsafeGarbageCollector( getAddrFromSymTable(prg.getSymTable().getContent().values()
                        ,prg.getHeapTable().getContent().values()), prg.getHeapTable().getContent()));
                repo.logPrgStateExec();
            }
        }
        catch (MyException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }*/
    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            //HERE you can call conservativeGarbageCollector
            //
            prgList.forEach(prg -> {
                prg.getHeapTable().setContent(unsafeGarbageCollector( getAddrFromSymTable(prg.getSymTable().getContent().values()
                        ,prg.getHeapTable().getContent().values()), prg.getHeapTable().getContent()));
            });
            //
            oneStepForAllPrg(prgList);
            //remove the completed programs
            //List<PrgState> prgList=removeCompletedPrg(repo.getPrgList())
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(prgList);
    }
    public List<PrgState> executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        if(prgList.size() > 0)
        {
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                System.out.println();
            }
            //prgList=removeCompletedPrg(repo.getPrgList());
            executor.shutdownNow();
        }
        return prgList;
    }
    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        //before the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        //RUN concurrently one step for each of the existing PrgStates
        //-----------------------------------------------------------------------
        //prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        //start the execution of the callables
        //it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> {
                    PrgState state=null;
                    try { state=future.get();}
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                        //System.exit(0);
                    }
                    return state;
                }).filter(p -> p!=null).collect(Collectors.toList());
        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        //after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        //Save the current programs in the repository
        repo.setPrgList(prgList);

    }

    /*public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();
        try {
            while (!prg.getStk().isEmpty()) {
                oneStep(prg);
                repo.logPrgStateExec();
                prg.getHeapTable().setContent(unsafeGarbageCollector( getAddrFromSymTable(prg.getSymTable().getContent().values()
                        ,prg.getHeapTable().getContent().values()), prg.getHeapTable().getContent()));
                repo.logPrgStateExec();
            }
        }
        catch (MyException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }*/

    // 4.3. display the current program state. You may want to display the program state after each
    //execution step if the display flag is set to on.
}
