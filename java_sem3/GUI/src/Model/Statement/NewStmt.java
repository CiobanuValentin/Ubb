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

public class NewStmt implements IStmt{
    private String varName;
    private Exp exp;
    public NewStmt(String n,Exp e){
        exp=e;
        varName=n;
    }

    @Override
    public String toString(){
        return "new("+varName+","+exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        if(symTbl.isDefined(varName)){
            Type typId= (symTbl.lookup(varName)).getType();
            if(typId instanceof RefType){
                Value v=exp.eval(symTbl,hp);
                //((RefValue)v).getType()
                Value v3=symTbl.lookup(varName);
                //if(v.getType().equals(typId)){
                if(v.getType().equals(((RefValue)v3).getLocationType())){
                    int x=hp.add(v);
                    //Create a new entry in the Heap table such that a new key (new free address)
                    // is generated and
                    //it is associated to the result of the expression evaluation
                    //Value v2= new RefValue(x,typId);
                    Value v2= new RefValue(x,v.getType());
                    symTbl.update(varName,v2);
                    //• in SymTable update the RefValue associated to the var_name such
                    // that the new RefValue
                    //has the same locationType and the address is equal to the new key
                    // generated in the Heap at
                    //the previous step

                }
                else throw new MyException("types do not match!");
            }
            else throw new MyException("the variable "+varName+" is not refType");
        }
        else throw new MyException("the used variable" +varName + " was not declared before");
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        Type typevar = typeEnv.lookup(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
}
