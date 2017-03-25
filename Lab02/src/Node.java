
public class Node<T extends Comparable<? super T>> implements Comparable<Node<T>> {

	private Node<T> leftChild = null, rightChild = null;
	private T nodeObject;

	public Node(T node) {
		nodeObject = node;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public int compareTo(Node<T> other) {
		return nodeObject.compareTo(other.getObject());
	}

	public T getObject() {
		return nodeObject;
	}

	@Override
	public String toString() {
		return "Object: " + nodeObject.toString()
				+ ((leftChild == null) ? "" : ", LeftChild: " + leftChild.nodeObject.toString())
				+ ((rightChild == null) ? "" : ", RightChild: " + rightChild.nodeObject.toString());
	}

}
