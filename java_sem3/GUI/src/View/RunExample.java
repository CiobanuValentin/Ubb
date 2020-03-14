package View;

import Controller.ControllerOld;
import Model.Exception.MyException;

public class RunExample extends Command {
    private ControllerOld ctr;
    public RunExample(String key, String desc, ControllerOld ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    @Override
    public void execute() {
        try{
            ctr.allStep();
        }
        catch (Exception e)  { System.out.println(e.getMessage());}
    }

    @Override
    public ControllerOld getCtrl() {
        return ctr;
    }
}
