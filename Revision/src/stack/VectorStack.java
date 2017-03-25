package stack;

import java.util.Vector;

public class VectorStack<T> {

    private Vector<T> stack;
    private Integer capcity;

    public VectorStack() {
        stack = new Vector<>();
        capcity = null;
    }

    public VectorStack(int capcity) {
        this.capcity = new Integer(capcity);
        stack = new Vector<>(this.capcity.intValue());
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

    public boolean isEmpty() {

        return stack.isEmpty();
    }

    public boolean isFull() {

        return (capcity != null && stack.size() >= capcity.intValue());
    }

    public void clear() {

        stack.clear();
    }

    @Override
    public String toString() {

        return peek().toString();
    }

}
