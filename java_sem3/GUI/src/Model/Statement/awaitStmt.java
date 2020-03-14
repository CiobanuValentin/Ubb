package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.concurrent.locks.ReentrantLock;

public class awaitStmt implements IStmt {
    private String id;
    private static ReentrantLock lock = new ReentrantLock();
    public awaitStmt(String idd){
        id=idd;
    }
    @Override
    public String toString() {
        return String.format("awaitCountDownLatch(%s)", id);
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIStack<IStmt> stk=state.getStk();
        //- foundIndex=lookup(SymTable,var). If var is not in SymTable or it has not
        //the type int then print an error message and terminate the execution.
        Value foundIndex = symTbl.lookup(id);// if it doesnt exist it throws exception in lookup
        Type typId=foundIndex.getType();
        if(!(typId.equals(new IntType()))) throw new MyException("foundIndex not int");
        //- if foundIndex is not an index in the LatchTable then
        //print an error message and terminate the execution
        // elseif LatchTable[foundIndex]==0 then
        // do nothing
        //else push back the await statement(that means the current PrgState must
        //wait for the countdownlatch to reach zero)
        IntValue v= (IntValue) foundIndex;
        Integer latchCount = state.getLatchTable().get(v.getVal()); // if it doesnt exist it throws error in get
        if(latchCount > 0) {
            stk.push(this);
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
