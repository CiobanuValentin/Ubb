package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.concurrent.locks.ReentrantLock;

public class newLatch implements IStmt{
    private String id;
    private Exp exp;
    private static ReentrantLock lock = new ReentrantLock();

    public newLatch(String idd,Exp e){
        id=idd;
        exp=e;
    }
    @Override
    public String toString() {
        return String.format("newCountDownLatch(%s, %s)", this.id, this.exp);
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        Value num1 = this.exp.eval(state.getSymTable(), state.getHeapTable());
        if(num1.getType().equals(new IntType())){
        Integer descriptor = state.getLatchTable().add(((IntValue)num1).getVal());
        symTbl.updateOrAdd(id, new IntValue(descriptor)); // update throws errors if any
        lock.unlock();
        }
        else {lock.unlock();
              throw new MyException("If num1 is not an integer then print an error and stop\n" +
                "the execution. ");}
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
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(new IntType()))
            if(typexp.equals(new IntType()))
                return typeEnv;
       throw new MyException("newLatch typecheck");
    }

}
