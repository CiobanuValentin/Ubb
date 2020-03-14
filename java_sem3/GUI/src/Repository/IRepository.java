package Repository;

import Model.Exception.MyException;
import Model.PrgState;

import java.util.List;

public interface IRepository {
    //PrgState getCrtPrg();
    void add(PrgState newPrg);
    void logPrgStateExec(PrgState prg) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
}
