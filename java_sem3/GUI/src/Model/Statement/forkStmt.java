package Model.Statement;

import Model.ADT.*;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public class forkStmt implements IStmt {
    //fork(Stmt) ?
    private IStmt stmt;
    public forkStmt(IStmt stmt){
        this.stmt=stmt;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState fork = new PrgState(state);
        fork.getStk().push(stmt);
        return fork;

    }
    @Override
    public String toString() {
        return "fork( "+stmt.toString()+" )";
    }

    /*@Override
    public Object clone() throws CloneNotSupportedException
    {
        ForkStatement clone = (ForkStatement) super.clone();
        clone.innerStatement = (IStatement) innerStatement.clone();
        return clone;
    }*/


    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        stmt.typecheck(typeEnv.copy());
        return typeEnv;

    }
}
