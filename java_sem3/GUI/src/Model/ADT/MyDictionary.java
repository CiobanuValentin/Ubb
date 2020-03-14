package Model.ADT;

import Model.Exception.MyException;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T,E> implements MyIDictionary<T,E> {
    private Map <T,E> dictionary;
    public MyDictionary(){
        dictionary=new HashMap<T,E>();
    }
    public   Map <T,E> getContent(){return dictionary;}

    @Override
    public void setContent(Map<T, E> content) {
        this.dictionary=content;
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
    public void update(T id, E val) throws MyException {
        if(id == null || val == null)
            throw new MyException("You can not add null values!");
        if(!this.dictionary.containsKey(id))
            throw new MyException("Element does not exist!");
        this.dictionary.put(id,val);
    }
    public void updateOrAdd(T id,E val)throws MyException{
        if(isDefined(id)) update(id,val);
        add(id,val);
    }

    @Override
    public MyIDictionary<T, E> copy() {
        /*HashMap<T, E> shallowCopy = new HashMap<T, E>();
        Set<Entry<T, E>> entries = originalMap.entrySet();
        for (Map.Entry<String, Employee> mapEntry : entries) {
            shallowCopy.put(mapEntry.getKey(), mapEntry.getValue());
        }*/
        HashMap<T, E> shallowCopy = new HashMap<>();
        Map<T,E> content=this.getContent();
        shallowCopy.putAll(content);
        MyIDictionary<T,E> copy=new MyDictionary<>();
        copy.setContent(shallowCopy);
        return copy;
    }

    @Override
    public void add(T id, E val) throws MyException {
        if(id == null || val == null)
            throw new MyException("You can not add null values!");
        if(this.dictionary.containsKey(id))
            throw new MyException("Element does exist!");
        this.dictionary.put(id,val);
    }
    @Override
    public String toString(){return dictionary.toString();}
}
