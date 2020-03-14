package Repository;

import Model.Exception.MyException;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Repository implements IRepository {
    private List<PrgState> list;
    private String logFilePath;
    public Repository(){
        list=new ArrayList<PrgState>();
        setPath();
    }
    public Repository(String path){
        list=new ArrayList<PrgState>();
        this.logFilePath=path;
    }
    private void setPath(){
        Scanner input=new Scanner(System.in);
        System.out.println("Give path:");
        logFilePath=input.nextLine();
    }

    @Override
    public List<PrgState> getPrgList(){
        return list;
    }

    @Override
    public void setPrgList(List<PrgState> list){
        this.list=list;
    }

    //@Override
    //public PrgState getCrtPrg() {
    //    return list.get(list.size()-1);
    //}

    @Override
    public void add(PrgState newPrg) {
        list.add(newPrg);
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException {
        PrintWriter logFile=null;
        try{
            logFile=new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            //logFile.printf(getCrtPrg().toString());
            logFile.printf(prg.toString());//s ar putea sa fie list.tostring
            logFile.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage()); // e.printStackTrace(); citeste
        }
    }
}
