

public class Node {

    private Node left, centre, right;
    private Data data;
    
    public Node(Data data) {
        this.data = data;
    }
    
    public Node getLeft() {
    
        return left;
    }

    
    public void setLeft(Node left) {
    
        this.left = left;
    }

    
    public Node getCentre() {
    
        return centre;
    }

    
    public void setCentre(Node centre) {
    
        this.centre = centre;
    }

    
    public Node getRight() {
    
        return right;
    }

    
    public void setRight(Node right) {
    
        this.right = right;
    }

    
    public Data getData() {
    
        return data;
    }

    
    public void setData(Data data) {
    
        this.data = data;
    }
    
    public String toString() {
        return data.toString() + ((left != null) ? "\tParent 1 <" + left.data.getName() : "?") + ((right != null) ? "\tParent 1 <" + right.data.getName() : "?") + "\n";
    }
}
