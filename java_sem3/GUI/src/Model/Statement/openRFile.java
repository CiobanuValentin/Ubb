package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt {
    private Exp exp;
    public openRFile(Exp exp){
        this.exp=exp;
    }
    @Override
    public String toString(){
        return "open("+exp.toString()+")";
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
        MyIStack<IStmt> stack=state.getStk();
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        Value v=exp.eval(symTbl,hp);
        Type typ=v.getType();
        if(typ.equals(new StringType())){
            if(!(fileTable.isDefined((StringValue) v))){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(((StringValue) v).getVal()));
                    //fileTable.update((StringValue) v,br);
                    fileTable.updateOrAdd((StringValue) v,br);
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
            else throw new MyException("File is already opened");
        }
        else{
            throw new MyException("wrong type of file to open");
        }
        //return state;
        return null;
    }
}
