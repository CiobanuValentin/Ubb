package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private Exp exp;
    public closeRFile(Exp exp){
        this.exp=exp;
    }
    @Override
    public String toString(){
        return "close("+exp.toString()+")";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expTyp=exp.typecheck(typeEnv);
        if(expTyp.equals(new StringType()))
            return typeEnv;
        throw new MyException("exp "+ exp.toString() +" is not stringType!");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        Value v=exp.eval(symTbl,hp);
        if(v.getType().equals(new StringType())){
            if(fileTable.isDefined((StringValue) v)){
                BufferedReader bf=fileTable.getValue((StringValue) v);
                try {
                    bf.close();
                    fileTable.remove((StringValue) v);
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }

            }
            else throw new MyException(v.toString()+" is not defined in the file table");
        }
        else throw new MyException("Value of expression "+exp.toString()+" is not a string");
        //return state;
        return null;
    }
}
