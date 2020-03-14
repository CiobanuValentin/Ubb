package Model.ADT;

import Model.Exception.MyException;

import java.util.List;

public interface MyIList<T> {
    void add(T elem);
    void delete(T elem) throws MyException;
    int size();
    T get(int index);
    String toString();
    List<T> getContent();
}
