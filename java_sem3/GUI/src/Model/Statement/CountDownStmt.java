package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyILatchTable;
import Model.ADT.MyIList;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.concurrent.locks.ReentrantLock;

public class CountDownStmt implements IStmt {
    private String id;
    private static ReentrantLock lock = new ReentrantLock();

    public CountDownStmt(String idd){
        id=idd;
    }
    @Override
    public String toString() {
        return String.format("countDownLatch(%s)", id);
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyILatchTable lt=state.getLatchTable();
        MyIList<Value> list= state.getOut();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        //- foundIndex=lookup(SymTable,var). If var is not in SymTable or it has not
        //the type int then print an error message and terminate the execution.
        Value foundIndex = symTbl.lookup(id);
        Type typId=foundIndex.getType();
        if(!(typId.equals(new IntType()))) throw new MyException("foundIndex not int");
        IntValue v= (IntValue) foundIndex;
        //- if foundIndex is not an index in the LatchTable then
        //print an error message and terminate the execution
        // elseif LatchTable[foundIndex] > 0 then
        // LatchTable[foundIndex]=LatchTable[foundIndex]-1;
        // write into Out table the current prgState id
        // else write into Out table the current prgState id
        Integer latchCount = lt.get(v.getVal());//// if it doesnt exist it throws error in get

        if(latchCount > 0)
        {
            lt.set(v.getVal(), latchCount - 1);
            list.add(new IntValue(state.getId()));
        }
        else
        {
            lt.set(v.getVal(), 0);
            list.add(new IntValue(state.getId()));
        }

        lock.unlock();
        return null;
    }
    //e.(0.75p). Implement the method typecheck for the statement newLatch(var,
    //exp) to verify if both var and exp have the type int. Implement the method
    //typecheck for the statement await(var) to verify if var has the type int.
    //Implement the method typecheck for the statement countDown(var) to verify if
    //var has the type int.
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        if(typevar.equals(new IntType()))
            return typeEnv;
        throw new MyException("await typecheck");
    }
}
