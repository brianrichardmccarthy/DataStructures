package queue;

import java.util.Collections;
import java.util.Vector;

public class PriorityQueue<T extends Comparable<? super T>> {

    private Vector<T> queue;
    private Integer capcity;

    public PriorityQueue() {
        this.capcity = null;
        queue = new Vector<>();
    }

    public PriorityQueue(int capcity) {
        this.capcity = new Integer(capcity);
        queue = new Vector<>(this.capcity.intValue());
    }

    public void enqueue(T t) {

        if (capcity != null && queue.size() >= capcity.intValue()) return;
        queue.add(t);
        Collections.sort(queue);
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
