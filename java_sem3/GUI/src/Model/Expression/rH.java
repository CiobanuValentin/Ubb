package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class rH implements Exp  {
    private Exp exp;
    public rH(Exp e){
        exp=e;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v=exp.eval(tbl,hp);
        if(v instanceof RefValue){
            int x=((RefValue)v).getAddr();
            if(hp.isDefined(x)){
                return hp.lookup(x);
            }
            else throw new MyException("the address does not exist in heap");
        }
        else throw new MyException(exp.toString()+" is not a refValue");
    }

    @Override
    public String toString(){
        return "rH("+exp.toString()+")";
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ=exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }

}
