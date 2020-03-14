package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value ee) {
        this.e=ee;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) {
        return e;
    }

    @Override
    public String toString() {
        return e+"";
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        return e.getType();
    }

}
