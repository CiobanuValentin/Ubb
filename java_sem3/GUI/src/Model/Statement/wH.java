package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class wH implements IStmt{
    private String varName;
    private Exp exp;
    public wH(String n,Exp e){
        exp=e;
        varName=n;
    }

    @Override
    public String toString(){
        return "wH("+varName+","+exp.toString()+")";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expTyp=exp.typecheck(typeEnv);
        Type varTyp=typeEnv.lookup(varName);
        if (varTyp.equals(new RefType(expTyp)))
            return typeEnv;
        throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        if (symTbl.isDefined(varName)){
            Type typId= (symTbl.lookup(varName)).getType();

            if (typId instanceof RefType){
                Value v=symTbl.lookup(varName);
                int x=((RefValue)v).getAddr();
                if(hp.isDefined(x)){
                    Value v2=exp.eval(symTbl,hp);
                    if(v2.getType().equals(((RefValue)v).getLocationType())){
                        hp.update(x,v2);
                    }
                    else throw new MyException("types not equal");
                }
                else throw new MyException("address not in heap");
            }
            else throw new MyException(varName+" is not RefType");
        }
        else throw new MyException("the used variable" +varName + " was not declared before");
        //return state;
        return null;
    }
}
