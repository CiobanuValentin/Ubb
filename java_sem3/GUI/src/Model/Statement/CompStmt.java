package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt snd;
    public CompStmt(IStmt first,IStmt second){
        this.first=first;
        this.snd=second;
    }

    @Override
    public String toString() {
        //return " ( "+first.toString() + " ; " + snd.toString()+" ) ";
        return first.toString() + " ; " + snd.toString();
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return snd.typecheck(first.typecheck(typeEnv));
    }

}
