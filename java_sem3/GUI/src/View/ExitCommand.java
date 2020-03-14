package View;

import Controller.ControllerOld;

public class ExitCommand extends Command {
    public ExitCommand(String key, String desc){
        super(key, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public ControllerOld getCtrl() {
        return null;
    }
}
