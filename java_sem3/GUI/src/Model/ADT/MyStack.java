package Model.ADT;

import Model.Type.Type;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack; //a field whose type CollectionType is an appropriate
    // generic java library collection
    public MyStack(){stack=new Stack<T>();}
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public Stack<T> getContent() {
        return stack;
    }

}
