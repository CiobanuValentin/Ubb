package Model.ADT;

import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    int size();
    String toString();
    Stack<T> getContent();
}
