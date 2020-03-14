package Model;

import Model.ADT.*;
import Model.Exception.MyException;
import Model.Statement.IStmt;
import Model.Value.RefValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIHeap<Integer,Value> heapTable;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyILatchTable latchTable;
    private MyIList<Value> out; //gs ?
    private IStmt originalProgram; //optional field, but good to have
    private int id;
    private static volatile int globalId = 1;
    public MyIHeap<Integer, Value> getHeapTable() {
        return heapTable;
    }
    public static synchronized int getGlobalIdId() {
        return globalId;
    }

    public static synchronized void setGlobalId(int globalId) {
        PrgState.globalId = globalId;
    }

    private static synchronized void incrementGlobalId() {
        globalId+=1;
    }
    public void setHeapTable(MyIHeap<Integer, Value> heapTable) {
        this.heapTable = heapTable;
    }
    public MyIStack<IStmt> getStk() {
        return exeStack;
    }
    public int getId(){return this.id;}
    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyILatchTable getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatchTable latchTable) {
        this.latchTable = latchTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public Boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    public PrgState(PrgState state){
        id=globalId;
        incrementGlobalId();
        //MyIStack<IStmt> stk1= new MyStack<>();
        exeStack=new MyStack<>();
        out=state.getOut();
        fileTable=state.getFileTable();
        heapTable=state.getHeapTable();
        symTable=state.getSymTable().copy();
        latchTable=state.getLatchTable();//
    }
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl,
                    MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> ft,
                    MyIHeap mh, IStmt prg){
        id=globalId;
        incrementGlobalId();
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable=ft;
        heapTable=mh;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        originalProgram=prg;//!!!
        stk.push(prg);
    }
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl,
                    MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> ft,
                    MyIHeap mh,MyILatchTable lt, IStmt prg){
        id=globalId;
        incrementGlobalId();
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable=ft;
        heapTable=mh;
        latchTable=lt;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        originalProgram=prg;//!!!
        stk.push(prg);
    }

    /*
    public static Map<Integer,Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value>
            heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}
    public static List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }
    */


    @Override
    public String toString(){
        return  "---PrgID--- \n"+this.id+"\n"+
                "---ExeStack--- \n"+exeStack.toString()+"\n"+
                "---SymTable--- \n"+symTable.toString()+"\n"+
                "---OutList--- \n"+out.toString()+"\n"+
                "---FileTable--- \n"+fileTable.toString()+"\n"+
                //"---LatchTable--- \n"+latchTable.toString()+"\n"+
                "---HeapTable--- \n"+heapTable.toString()+"\n"+"\n"+"\n";

    }

}
