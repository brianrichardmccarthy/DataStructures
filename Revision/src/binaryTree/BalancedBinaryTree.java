package binaryTree;

@SuppressWarnings("unused")
public class BalancedBinaryTree<T extends Comparable<? super T>> {

    private Node<T> root;
    private class Height {
        int height;
    }
    
    public BalancedBinaryTree() {
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
    
    public boolean isBalance(Node<T> node, Height height) {
        
        if (node == null) {
            height.height = 0;
            return true;
        }
        
        Height leftHeight = new Height(), rightHeight = new Height();
        boolean left = isBalance(node.getLeft(), leftHeight);
        boolean right = isBalance(node.getRight(), rightHeight);
        
        height.height = ((leftHeight.height > rightHeight.height) ? leftHeight.height : rightHeight.height)+1;
        
        return !(leftHeight.height - rightHeight.height >= 2 || rightHeight.height - leftHeight.height >= 2);
        
    }
    
    private Node<T> rotateRight(Node<T> t) {
        Node<T> temp = t.getLeft();
        t.setLeft(temp.getRight());
        temp.setRight(t);
        return temp;
    }
    
    public static void main(String[] args) {
        BalancedBinaryTree<Integer> b = new BalancedBinaryTree<>();
        b.root = new Node<Integer>(1);
        b.root.setLeft(new Node<Integer>(new Integer(2)));
        b.root.setRight(new Node<Integer>(new Integer(3)));
        b.root.getLeft().setLeft(new Node<Integer>(new Integer(4)));
        b.root.getLeft().setRight(new Node<Integer>(new Integer(5)));
        b.root.getRight().setLeft(new Node<Integer>(new Integer(6)));
        b.root.getRight().setRight(new Node<Integer>(new Integer(7)));
        
        System.out.println("Is balanced <" + b.isBalance(b.root, b.new Height()) + ">");
        
    }
    
    private Node<T> rotateLeft(Node<T> t) {
        Node<T> temp = t.getRight();
        t.setRight(temp.getLeft());
        temp.setLeft(t);
        return temp;
    }
    
    private Node<T> rotateRightLeft(Node<T> t) {
        Node<T> temp = t.getRight();
        t.setRight(rotateRight(temp));
        return rotateLeft(t);
    }
    
    private Node<T> rotateLeftRight(Node<T> t) {
        Node<T> temp = t.getLeft();
        t.setLeft(rotateLeft(temp));
        return rotateRight(t);
    }
    
}
