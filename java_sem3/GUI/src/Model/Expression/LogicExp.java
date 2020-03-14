package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class  LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; //1 for && 2 for ||

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1=e1.eval(tbl,hp);
        if(v1.getType().equals(new BoolType())){
            v2=e2.eval(tbl,hp);
            if(v2.getType().equals(new BoolType())){
                boolean b1,b2;
                b1=((BoolValue)v1).getVal();
                b2=((BoolValue)v2).getVal();
                if(op==1)return new BoolValue(b1 && b2);
                else if(op==2)return new BoolValue(b1 || b2);
                else throw new MyException("wrong operator");
            }
            else throw new MyException("Second operand is not of type bool");
        }
        else throw new MyException("First operand is not bool");
    }

    @Override
    public String toString() {
        return e1.toString()+op+e2.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not a bool");
        }else
            throw new MyException("first operand is not a bool");
    }
}
