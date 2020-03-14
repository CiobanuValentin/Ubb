package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class VarDeclStmt implements IStmt{
    private String name;
    private Type typ;

    public VarDeclStmt(String v, Type t) {
        name=v;
        typ=t;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        if(symTbl.isDefined(name))
            throw new MyException(name+" already defined");
        else
            symTbl.add(name,typ.defaultValue());
        //return state;
        return null;
    }

    @Override
    public String toString() {
        return "("+typ+" "+name+")";
    }

    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        typeEnv.add(name,typ);
        return typeEnv;
    }

}
