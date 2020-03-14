package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {
    private Exp exp;
    private String varName;
    public readFile(Exp exp, String var){
        this.exp=exp;
        varName=var;
    }

    public String toString(){
        return "read("+varName+","+exp.toString()+")";
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
        MyIDictionary<String, Value> symTable=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeapTable();
        MyIDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        if(symTable.isDefined(varName)){
            Value v=symTable.getValue(varName);
            if(v.getType().equals(new IntType())){
                Value v2=exp.eval(symTable,hp);
                if(v2.getType().equals(new StringType())){
                    if(fileTable.isDefined((StringValue) v2)){
                        BufferedReader br=fileTable.getValue((StringValue) v2);
                        try {
                            String read=br.readLine();
                            IntValue val;
                            if(read==null){
                                val=new IntValue(0);
                            }
                            else{
                                val=new IntValue(Integer.parseInt(read));
                            }
                            symTable.update(varName,val);
                        }
                        catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else throw new MyException("expr isn't defined in the file table");
                }
                else throw new MyException("Expression isn't a string type");
            }
            else throw new MyException("value of "+varName+" is not int");
        }
        else throw new MyException(varName+" is not defined");
        //return state;
        return null;
    }
}
