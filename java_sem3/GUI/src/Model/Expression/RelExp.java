package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelExp implements Exp {

    private Exp e1;
    private Exp e2;
    private String op;

    public RelExp( Exp valueExp, Exp valueExp1,String c) {op=c;e1=valueExp;e2=valueExp1;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                int n1,n2;
                n1= ((IntValue)v1).getVal();
                n2= ((IntValue)v2).getVal();
                if (op.equals("<")) return new BoolValue(n1<n2);
                if (op.equals(">")) return new BoolValue(n1>n2);
                if (op.equals("<=")) return new BoolValue(n1<=n2);
                if (op.equals(">=")) return new BoolValue(n1>=n2);
                if (op.equals("==")) return new BoolValue(n1==n2);
                if (op.equals("!=")) return new BoolValue(n1!=n2);
                throw new MyException("Invalid Operator");
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
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
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                //return new IntType();
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
