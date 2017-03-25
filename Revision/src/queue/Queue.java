package queue;

import java.util.Vector;

public class Queue<T> {

    Vector<T> queue;
    Integer capcity;

    public Queue() {
        queue = new Vector<>();
        capcity = null;
    }

    public Queue(int capcity) {
        this.capcity = new Integer(capcity);
        queue = new Vector<>(this.capcity.intValue());
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
