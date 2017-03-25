/**
 * Binary tree class. Holds a pointer to this DataItem and it's children. 
 * Contains methods for getting the encoding of the Character, printing the tree in pre-order, inorder, and post-order. 
 * As well as Mutators and Accessors.
 * 
 * @author Brian McCarthy, 20063914
 *
 */
public class Node {

    private DataItem thisItem;
    private Node leftChild;
    private Node rightChild;

    /**
     * Constructs a new instance of Node with the DataItem as its' data
     * 
     * @param DataItem
     *            thisItem
     */
    public Node(DataItem thisItem) {
        setThisItem(thisItem);
    }

    /**
     * Constructs an empty Node
     */
    public Node() {
    }

    /**
     * Accessors for the data item in this node
     * 
     * @return DataItem
     */
    public DataItem getThisItem() {

        return thisItem;
    }

    /**
     * Accessor for the left child of this node
     * 
     * @return Node
     */
    public Node getLeftChild() {

        return leftChild;
    }

    /**
     * Accessor for the Right child of this node
     * 
     * @return Node
     */
    public Node getRightChild() {

        return rightChild;
    }

    /**
     * Mutator for this DataItem
     * 
     * @param thisItem
     *            (DataItem)
     */
    public void setThisItem(DataItem thisItem) {

        this.thisItem = thisItem;
    }

    /**
     * Mutator for this left child
     * 
     * @param leftChild
     *            (Node)
     */
    public void setLeftChild(Node leftChild) {

        this.leftChild = leftChild;
    }

    /**
     * Mutator for this right child
     * 
     * @param rightChild
     *            (Node)
     */
    public void setRightChild(Node rightChild) {

        this.rightChild = rightChild;
    }

    /**
     * Recursively set the binary value of the data item. Set it to 1 if on the right or 0 if on the left. Will only set it if the currentNode is a leaf node.
     * 
     * @param currentNode
     *            (Node)
     * @param binaryValue
     *            (String)
     */
    public void setBinaryValue(Node currentNode, String binaryValue) {

        if (currentNode == null) return;

        if (currentNode.leftChild != null) setBinaryValue(currentNode.leftChild, binaryValue + "0");

        if (currentNode.rightChild != null) setBinaryValue(currentNode.rightChild, binaryValue + "1");

        if (currentNode.rightChild == null && currentNode.leftChild == null) currentNode.thisItem.setBinary(
            binaryValue);

    }

    @Override
    public String toString() {

        return thisItem.toString();
    }

    /**
     * Print this node data. Then print it's left child and it's data and it's children. Then print it's right child data and it's children.
     * 
     * @param root
     *            (Node)
     */
    public static void printPreOrder(Node root, String indent) {

        if (root == null) return;

        System.out.println(indent + root);

        if (root.leftChild != null) printPreOrder(root.leftChild, indent + "\t");

        if (root.rightChild != null) printPreOrder(root.rightChild, indent + "\t");

    }

    /**
     * Print left child data and it's children. Then print this node's data. Then print right child data and it's children.
     * 
     * @param root
     *            (Node)
     */
    public static void printInOder(Node root) {

        if (root == null) return;

        if (root.leftChild != null) printInOder(root.leftChild);

        System.out.println(root);

        if (root.rightChild != null) printInOder(root.rightChild);
    }

    /**
     * Print left child and it's children. Then print right child data and it's children. Then print this node's data.
     * 
     * @param root
     *            (Node)
     */
    public static void printPostOrder(Node root) {

        if (root == null) return;

        if (root.leftChild != null) printPostOrder(root.leftChild);

        if (root.rightChild != null) printPostOrder(root.rightChild);

        System.out.println(root);
    }

}
