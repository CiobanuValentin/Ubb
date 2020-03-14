package Model.ADT;

import Model.Exception.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyLatchTable implements MyILatchTable{
    private Map<Integer, Integer> table; // poate faci generic
    private Integer index;
    public MyLatchTable() {
        this.table = new HashMap<>();
        this.index = 0;
    }
    public Integer add(Integer value) {
        this.table.put(index, value);
        index += 1;
        return index - 1;
    }
    public Integer get(Integer key) throws MyException {
        if(this.table.get(key) == null) {
            throw new MyException("get: invalid latch identifier");
        } else {
            return this.table.get(key);
        }
    }
    public void set(Integer key, Integer value) throws MyException {
        if(this.table.get(key) == null) {
            throw new MyException("set: invalid latch identifier");
        } else {
            this.table.put(key, value);
        }
    }
    public Map getContent(){return this.table;}

}

