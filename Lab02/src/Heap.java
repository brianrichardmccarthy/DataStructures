import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {
	private ArrayList<Node<T>> items;

	/**
	 * Constructor
	 */
	public Heap() {
		items = new ArrayList<>();
	}

	@Override
	public void add(T t) {
		items.add(new Node<>(t));
		siftUp();
	}

	@Override
	public void siftUp() {
		int k = items.size() - 1;
		while (k > 0) {
			int p = (k - 1) / 2;
			Node<T> item = items.get(k);
			Node<T> parent = items.get(p);

			if (item.getObject().compareTo(parent.getObject()) > 0) {
				Node<T> temp = items.get(k);
				items.set(k, parent);
				items.set(p, temp);
				k = p;
			} else {
				break;
			}
		}
		setChild();
	}

	@Override
	public void siftDown() {
		int k = 0;
		int l = 2 * k + 1;
		while (l < items.size()) {
			int max = l, r = l + 1;
			if (r < items.size()) {

				if (items.get(r).getObject().compareTo(items.get(l).getObject()) > 0) {
					max++;
				}
			}
			if (items.get(k).getObject().compareTo(items.get(max).getObject()) < 0) {
				Node<T> temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max, temp);
				k = max;
				l = 2 * k + l;
			} else {
				break;
			}
		}
		setChild();
	}

	@Override
	public T removeMax() {
		if (items.size() == 0) {
			throw new NoSuchElementException();
		}
		if (items.size() == 1) {
			return items.remove(0).getObject();
		}

		Node<T> temp = items.get(0);
		items.set(0, items.remove(items.size() - 1));
		siftDown();
		return temp.getObject();
	}

	@Override
	public T getMax() {
		return (isEmpty()) ? null : items.get(0).getObject();
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public String toString() {
		String string = "";

		for (Node<T> t : items) {
			string += t.getObject().toString();
		}

		return string;
	}

	@Override
	public T breadthFirstSearch(T t) {
		if (t == null) {
			return null;
		}
		int x = 0;
		Queue<Node<T>> visitedNodes = new Queue<>();
		visitedNodes.enqueue(items.get(0));
		while (!visitedNodes.isEmpty()) {
			Node<T> current = visitedNodes.dequeue();
			if (current != null && current.getObject().compareTo(t) == 0) {
				System.out.println("Traversal: " + x);
				return current.getObject();
			}

			if (current.getLeftChild() != null) {
				visitedNodes.enqueue(current.getLeftChild());
			}

			if (current.getRightChild() != null) {
				visitedNodes.enqueue(current.getRightChild());
			}

			x++;
		}
		return null;
	}

	private void setChild() {
		for (int x = 0; x * 2 + 1 < items.size() || x * 2 + 2 < items.size(); x++) {
			if ((x * 2 + 1) < items.size())
				items.get(x).setLeftChild(items.get((x * 2 + 1)));
			if ((x * 2 + 2) < items.size())
				items.get(x).setRightChild(items.get((x * 2 + 2)));
		}
	}

}
