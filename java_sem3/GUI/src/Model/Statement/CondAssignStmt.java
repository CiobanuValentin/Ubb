package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;

public class CondAssignStmt implements IStmt {
    private Exp exp1;
    private Exp exp2;
    private Exp exp3;
    private String id;

    public CondAssignStmt(Exp e1,Exp e2,Exp e3,String v){
        exp1=e1;
        exp2=e2;
        exp3=e3;
        id=v;
    }
    @Override
    public String toString(){
        return ""+id+"="+exp1.toString()+"?"+exp2.toString()+":"+exp3.toString();
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();

        stk.push(new IfStmt(exp1,new AssignStmt(id,exp2),new AssignStmt(id,exp3)));

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp1.typecheck(typeEnv);
        if (typexp.equals(new BoolType())){
            Type typexp2 = exp2.typecheck(typeEnv);
            Type typexp3 = exp3.typecheck(typeEnv);
            if(typevar.equals(typexp2)){
                if(typevar.equals((typexp3))){
                    return typeEnv;
                }
            }
            throw new MyException(" v, exp2, and exp3 do not have the same type");
        }
        else throw new MyException("exp1 is not bool type");
    }
}
