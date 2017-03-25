package linkedList;

public class Node<T extends Comparable<? super T>> {

    public T data;
    public Node<T> next, previous;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data, Node<T> next, Node<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString() {

        return "<" + data.toString() + ">";
    }

}
