package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIList;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt{
    private Exp exp;

    public PrintStmt(Exp v) {
        exp=v;
    }

    @Override
    public String toString(){
        return "print(" +exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> list= state.getOut();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        list.add(exp.eval(symTbl,hp));
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }

}
