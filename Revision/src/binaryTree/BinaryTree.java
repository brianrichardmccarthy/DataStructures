package binaryTree;


public class BinaryTree<T extends Comparable<? super T>> {

    private Node<T> root;
    
    public BinaryTree() {
    }

    public T getRootData() {
        return root.getData();
    }
    
    public int getHeight() {
        return 0;
    }
    
    public int getNumberOfNodes() {
        return 0;
    }

    public void addNode(T t) {
        
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void clear() {
        root = null;
    }
    
}
