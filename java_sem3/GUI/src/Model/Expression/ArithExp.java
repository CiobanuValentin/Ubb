package Model.Expression;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithExp implements Exp {

    private Exp e1;
    private Exp e2;
    private char op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char c, Exp valueExp, Exp valueExp1) {op=c;e1=valueExp;e2=valueExp1;
    }

    //override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                //IntValue i1 = (IntValue)v1; //astea 2 randuri nu cred ca tb; cred ca la n1 si n2 tb sa fie in drpt i1 in loc de intvalue v1
                //IntValue i2 = (IntValue)v2;
                int n1,n2;
                //n1=0;n2=0;//remove this line when u implement getVal;
                n1= ((IntValue)v1).getVal();
                n2= ((IntValue)v2).getVal();
                if (op=='+') return new IntValue(n1+n2);
                if (op =='-') return new IntValue(n1-n2);
                if(op=='*') return new IntValue(n1*n2);
                if(op=='/')
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
                //if it  gets here->op not 1-2-3-4
                throw new MyException("Invalid Operator");
            }else
                throw new MyException("second operand isn't an int!!!");
        }else
            throw new MyException("first operand isn't an int!!!");
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
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}



