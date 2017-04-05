package linkedList;

public class LinkedList<T extends Comparable<? super T>> {

    private Node<T> node;

    public LinkedList() {
        node = null;
    }

    public void add(T data) {

        node = new Node<>(data, node);
    }

    public Node<T> remove() {

        if (node == null) return null;

        Node<T> toRemove = node;

        node = node.next;

        return toRemove;
    }

    public Node<T> remove(T data) {

        Node<T> current, previous;
        previous = current = node;

        while (current.data.compareTo(data) != 0) {
            if (current.next == null) return null;
            previous = current;
            current = current.next;
        }

        if (current == node) return remove();
        previous.next = current.next;
        return current;

    }

    public void display() {

        Node<T> current = node;

        while  (current != null) {
            System.out.print("Current " + current);
            System.out.println( (current.next != null) ? ("\tNext " + current.next.toString()) : "");
            current = current.next;
        }
        System.out.println();
    }

    public Node<T> find(T data) {

        return find(new Node<>(data));
    }

    public Node<T> find(Node<T> search) {

        Node<T> current = this.node;

        do {
            if (current.data.compareTo(search.data) == 0) return current;
        } while ( (current = current.next) != null);
        System.out.println("Item <" + search.data.toString() + "> was not found");
        return null;
    }
    
    public void reverse() {
        
        Node<T> prev = null, current = node, next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } 
        node = prev;
        
    }

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.display();
        list.reverse();
        list.display();
    }

}