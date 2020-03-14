package Model.ADT;

import Model.Exception.MyException;

import java.util.Map;

public interface MyILatchTable {
    Integer add(Integer value);
    Integer get(Integer key) throws MyException;
    void set(Integer key, Integer value) throws MyException;
    Map getContent();
}
