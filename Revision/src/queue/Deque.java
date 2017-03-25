package queue;

import java.util.Vector;

public class Deque<T> {

    Vector<T> queue;
    Integer capcity;

    public Deque() {
        queue = new Vector<>();
        capcity = null;
    }

    public Deque(int capcity) {
        this.capcity = new Integer(capcity);
        queue = new Vector<>(this.capcity.intValue());
    }

    public void push(T t) {

        if (capcity != null && queue.size() >= capcity.intValue()) return;
        queue.add(0, t);
    }

    public T pop() {

        return (!isEmpty()) ? queue.remove(queue.size() - 1) : null;
    }

    public T peek() {

        return (!isEmpty()) ? queue.get(queue.size() - 1) : null;
    }

    public void enqueue(T t) {

        if (capcity != null && queue.size() >= capcity.intValue()) return;
        queue.add(t);
    }

    public T dequeue() {

        return (!isEmpty()) ? queue.remove(0) : null;
    }

    public T getFront() {

        return (!isEmpty()) ? queue.get(0) : null;
    }

    public boolean isEmpty() {

        return queue.isEmpty();
    }

    public void clear() {

        queue.clear();
    }

}
