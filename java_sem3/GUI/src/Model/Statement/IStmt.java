package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    //which is the execution method for a statement.
    String toString();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException;
}
