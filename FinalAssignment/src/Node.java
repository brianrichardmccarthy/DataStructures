
public class Node {

    private Node leftChild, middleChild, rightChild, leftParent, rightParent;
    private Data data;

    public Node(Data data) {
        this.data = data;
    }

    public Node getLeft() {

        return leftChild;
    }

    public void setLeft(Node left) {

        this.leftChild = left;
    }

    public Node getCentre() {

        return middleChild;
    }

    public void setCentre(Node centre) {

        this.middleChild = centre;
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

        return data.toString();
    }

    public Node getLeftParent() {

        return leftParent;
    }

    public void setLeftParent(Node leftParent) {

        this.leftParent = leftParent;
    }

    public Node getRightParent() {

        return rightParent;
    }

    public void setRightParent(Node rightParent) {

        this.rightParent = rightParent;
    }

    public int addChild(Data data) {

        int result = 0;

        if (!data.getParentOne().equals("?")) {
            if (data.getParentOne().equals(this.data.getName())) {
                if (leftChild == null) leftChild = new Node(data);
                else if (rightChild == null) rightChild = new Node(data);
                else middleChild = new Node(data);
                result++;
            } else {
                if (leftChild != null) result += leftChild.addChild(data);
                if (middleChild != null) result += middleChild.addChild(data);
                if (rightChild != null) result += rightChild.addChild(data);
            }
        } else if (!data.getParentTwo().equals("?")) {
            if (data.getParentTwo().equals(this.data.getName())) {
                if (leftChild == null) leftChild = new Node(data);
                else if (rightChild == null) rightChild = new Node(data);
                else middleChild = new Node(data);
                result++;
            } else {
                if (leftChild != null) result += leftChild.addChild(data);
                if (middleChild != null) result += middleChild.addChild(data);
                if (rightChild != null) result += rightChild.addChild(data);
            }
        }

        return result;
    }
    
    public void print(Node node, String indent) {
        if (node == null) return;
        
        print(node.leftChild, indent + "\t");
        System.out.println(indent + node.toString());
        print(node.rightChild, indent+"\t");
    }
    
}
