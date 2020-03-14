package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt{
    private String id;
    private Exp exp;
    public AssignStmt(String id,Exp exp){
        this.id=id;
        this.exp=exp;
    }

    @Override
    public String toString(){ return "("+id+"="+ exp.toString()+")";}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        Value val = exp.eval(symTbl,hp);
        if (symTbl.isDefined(id)){
            Type typId= (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else throw new MyException("declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else throw new MyException("the used variable" +id + " was not declared before");
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}
