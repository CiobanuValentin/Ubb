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

public class WhileStmt implements IStmt {
    private Exp exp;
    IStmt stmt;
    public WhileStmt(Exp exp,IStmt stmt){
        this.exp=exp;
        this.stmt=stmt;
    }

    @Override
    public String toString(){
        return "WHILE("+exp.toString() + ") {"+stmt.toString()+"}";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        Value val=exp.eval(symTbl,hp);
        if(val.getType().equals(new BoolType())){
            if (((BoolValue)val).getVal()){
                stk.push(this);
                stk.push(stmt);
            }
            //return state;
            return null;
            // else do nothing,it s already poped
        }
        else throw new MyException("Condition exp is not boolean");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

}
