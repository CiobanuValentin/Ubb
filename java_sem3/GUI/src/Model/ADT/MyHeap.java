package Model.ADT;

import Model.Exception.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T,E> implements MyIHeap<T,E> {
    private Map<Integer,E> dictionary;
    //. Heap must manage the unicity of the addresses, therefore it must have
    //a field that denotes the new free location. The addresses start from 1. The address 0 is considered an
    //invalid address (namely null).
    private int free;
    public MyHeap(){
        free=0;
        dictionary=new HashMap<>();
    }
    public void setContent(Map<Integer,E> x){
        dictionary=x;
    }
    public  Map<Integer,E> getContent()
    {
        return dictionary;
    }
    @Override
    public E lookup(T id) throws MyException {
        if(!dictionary.containsKey(id))
            throw new MyException("This element does not exist!");
        return this.dictionary.get(id);
    }

    @Override
    public E getValue(T id) {
        return dictionary.get(id);
    }

    @Override
    public boolean isDefined(T id) {
        return dictionary.containsKey(id);
    }

    @Override
    public void remove(T id) {
        dictionary.remove(id);
    }

    @Override
    public void update(Integer id, E val) throws MyException {
        if(id == null || val == null)
            throw new MyException("You can not add null values!");
        if(!this.dictionary.containsKey(id))
            throw new MyException("Element does not exist!");
        this.dictionary.put(id,val);
    }

    @Override
    public int add(E val) throws MyException {
        if(val == null)
            throw new MyException("You can not add null values!");
        //if(this.dictionary.containsKey(id))
        //    throw new MyException("Element does exist!");
        free=free+1;
        this.dictionary.put(free,val);
        return free;
    }
    @Override
    public String toString(){return dictionary.toString();}
}
