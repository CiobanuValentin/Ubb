package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements IStmt{
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp=e;
        thenS=t;
        elseS=el;
    }

    @Override
    public String toString(){
        return "IF("+ exp.toString()+") THEN(" +thenS.toString()
                +")ELSE("+elseS.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        Value val=exp.eval(symTbl,hp);
        if(val.getType().equals(new BoolType()))
        {
            if (((BoolValue)val).getVal()){stk.push(thenS);}
            else stk.push(elseS);
        }
        else throw new MyException("Cond expr is not a boolean!");

        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            //thenS.typecheck(clone(typeEnv));
            //elseS.typecheck(clone(typeEnv));
            thenS.typecheck(typeEnv.copy());
            elseS.typecheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

}
