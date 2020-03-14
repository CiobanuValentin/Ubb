package Model.ADT;

import Model.Exception.MyException;

import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> list;
    public MyList() {list=new LinkedList<T>(); }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public void delete(T elem) throws MyException {
        //oare tb cand lista e empty sa dau throw ?
        if(list.isEmpty()) throw new MyException("List is empty!");
        if(list.contains(elem)) list.remove(elem);
        else throw new MyException("Element does not exist!");
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int index) {
        //maybe check for index >size;
        return list.get(index);
    }
    @Override
    public String toString()
    {
        return list.toString();
    }

    @Override
    public List<T> getContent() {
        return list;
    }

    //need iterator?
}
