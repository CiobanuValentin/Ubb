package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp)throws MyException;
    String toString();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
