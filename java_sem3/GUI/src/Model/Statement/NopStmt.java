package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt {
    @Override
    public String toString(){return "nop;";}

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //cred ca asta i tot
        //return state;
        return null;
    }
}
