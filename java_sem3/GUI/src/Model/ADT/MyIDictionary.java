package Model.ADT;

import Model.Exception.MyException;

import java.util.Map;

public interface MyIDictionary<T,E> {
    E lookup(T id) throws MyException;//vezi daca e e sau value sau whatever altcv; cred ca e e;
    E getValue (T id);
    boolean isDefined(T id);
    void remove(T id);
    void update(T id, E val) throws MyException;
    String toString();
    void add(T id, E val) throws MyException;
    Map<T,E> getContent();
    void setContent(Map<T,E> content);
    void updateOrAdd(T id,E val)throws MyException;
    MyIDictionary<T,E> copy();

}
