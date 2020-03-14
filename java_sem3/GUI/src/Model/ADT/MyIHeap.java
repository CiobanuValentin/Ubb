package Model.ADT;

import Model.Exception.MyException;

import java.util.Map;

public interface MyIHeap<T,E> {
    E lookup(T id) throws MyException;//vezi daca e e sau value sau whatever altcv; cred ca e e;
    E getValue (T id);
    boolean isDefined(T id);
    void remove(T id);
    void update(Integer id, E val) throws MyException;
    String toString();
    int add( E val) throws MyException;
    void setContent(Map<Integer,E> x);
    Map<Integer,E> getContent();
}
