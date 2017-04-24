
public class Node {

    private Node leftChild, middleChild, rightChild, leftParent, rightParent;
    private Data data;

    public Node(Data data) {
        setData(data);
    }

    public Node(Data data, Node parent, boolean left) {
        setData(data);
        if (left) setLeftParent(parent);
        else setRightParent(parent);
    }

    public Node getLeft() {

        return leftChild;
    }

    public void setLeftChild(Node left) {

        this.leftChild = left;
    }

    public Node getCentre() {

        return middleChild;
    }

    public void setCentre(Node centre) {

        if (!middleChild.data.equals("?")) this.middleChild = centre;
    }

    public Node getRight() {

        return rightChild;
    }

    public void setRight(Node right) {

        this.rightChild = right;
    }

    public Data getData() {

        return data;
    }

    public void setData(Data data) {

        this.data = data;
    }

    public String toString() {

        return data.toString() + ( (leftParent != null) ? " has left parent" : "") + ( (rightParent != null) ? " has right parent" : "") + ( (leftChild != null) ? " has left child" : "") + ( (rightChild != null) ? " has right parent" : "");
    }

    public Node getLeftParent() {

        return leftParent;
    }

    public void setLeftParent(Node leftParent) {
        if (!leftParent.data.equals("?")) this.leftParent = leftParent;
    }

    public Node getRightParent() {

        return rightParent;
    }

    public void setRightParent(Node rightParent) {
        if (!rightParent.data.equals("?")) this.rightParent = rightParent;
    }

    public static int addChild(Data child, Node parent) {

        int result = 0;

        if (!child.getParentOne().startsWith("?")) {
            if (child.getParentOne().equals(parent.data.getName())) {
                if (parent.leftChild == null) parent.leftChild = new Node(child, parent, true);
                else if (parent.rightChild == null) parent.rightChild = new Node(child, parent, true);
                else parent.middleChild = new Node(child, parent, true);
                result++;
            } else {
                if (parent.leftChild != null) result += addChild(child, parent.leftChild);
                else if (parent.middleChild != null) result += addChild(child, parent.middleChild);
                else if (parent.rightChild != null) result += addChild(child, parent.rightChild);
            }
        }
        if (!child.getParentTwo().startsWith("?")) {
            // System.out.println("Child <" + child.toString() + ">\nThe Parent <" + parent.toString() + ">");
            // System.out.println("child.getParentTwo() <" + child.getParentTwo() + ">: " + child.getParentTwo().charAt(0));
            if (child.getParentTwo().equals(parent.data.getName())) {
                if (parent.leftChild == null) parent.leftChild = new Node(child, parent, false);
                else if (parent.rightChild == null) parent.rightChild = new Node(child, parent, false);
                else parent.middleChild = new Node(child, parent, false);
                result++;
            } else {
                if (parent.leftChild != null) result += addChild(child, parent.leftChild);
                else if (parent.middleChild != null) result += addChild(child, parent.middleChild);
                else if (parent.rightChild != null) result += addChild(child, parent.rightChild);
            }
        }

        return result;
    }

    public void print(Node node, String indent) {

        if (node == null) return;

        print(node.leftChild, indent + "\t");
        System.out.println(indent + node.toString());
        print(node.rightChild, indent + "\t");
    }

}
