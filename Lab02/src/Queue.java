import java.util.Vector;

public class Queue<T> implements QueueInterface<T> {

	private Vector<T> queue;

	public Queue() {
		queue = new Vector<>();
	}

	@Override
	public void enqueue(T t) {
		queue.add(t);
	}

	@Override
	public T dequeue() {
		return (isEmpty()) ? null : queue.remove(0);
	}

	@Override
	public T getFront() {
		return (isEmpty()) ? null : queue.get(0);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void clear() {
		queue.clear();
	}

	public int size() {
		return queue.size();
	}

}
