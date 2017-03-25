package stack;

import java.util.ArrayList;

public class ArrayListStack<T> {

    private ArrayList<T> stack;
    private Integer capcity;

    public ArrayListStack() {
        stack = new ArrayList<>();
        capcity = null;
    }

    public ArrayListStack(int capcity) {
        this.capcity = new Integer(capcity);
        stack = new ArrayList<>(this.capcity.intValue());
    }

    public T pop() {

        return (!isEmpty()) ? stack.remove(stack.size() - 1) : null;
    }

    public T peek() {

        return (!isEmpty()) ? stack.get(stack.size() - 1) : null;
    }

    public void push(T t) {

        if (capcity != null && stack.size() >= capcity.intValue()) return;
        stack.add(t);
    }

    public void clear() {

        stack.clear();
    }

    public boolean isEmpty() {

        return stack.isEmpty();
    }

    public boolean isFull() {

        return (capcity != null && stack.size() >= capcity.intValue());
    }

    @Override
    public String toString() {

        return peek().toString();
    }

}
