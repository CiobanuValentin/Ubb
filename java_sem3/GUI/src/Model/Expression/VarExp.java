package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Exp {
    private String id;
    public  VarExp(String id){
        this.id=id;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        return tbl.lookup(id);
    }
    @Override
    public String toString() {
        return id+"";
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv.lookup(id);
    }
}
