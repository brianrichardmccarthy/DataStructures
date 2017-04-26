import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * 
 * @author Brian
 *
 */
public class Node implements Comparable<Node> {

    private static HashMap<String, Node> nodes = new HashMap<>();
    private static HashMap<String, Integer> families = new HashMap<>();

    private String name;
    private String leftChildName;
    private String rightChildName;
    private String middleChildName;
    private String parentOneName;
    private String parentTwoName;
    private Integer yearOfBirth;
    private Character gender;
    private int familyIndex;

    /**
     * 
     * @param thisName
     * @param gender
     * @param year
     * @param parentOne
     * @param parentTwo
     */
    public Node(String thisName, Character gender, Integer year, String parentOne, String parentTwo) {
        setName(thisName);
        setGender(gender);
        setYearOfBirth(year);
        setParentOneName(parentOne);
        setParentTwoName(parentTwo);
    }

    /**
     * 
     * @return
     */
    public String getName() {

        return name;
    }

    /**
     * 
     * @return
     */
    public String getLeftChildName() {

        return leftChildName;
    }

    /**
     * 
     * @return
     */
    public String getRightChildName() {

        return rightChildName;
    }

    /**
     * 
     * @return
     */
    public String getMiddleChildName() {

        return middleChildName;
    }

    /**
     * 
     * @return
     */
    public String getParentOneName() {

        return parentOneName;
    }

    /**
     * 
     * @return
     */
    public String getParentTwoName() {

        return parentTwoName;
    }

    /**
     * 
     * @return
     */
    public Integer getYearOfBirth() {

        return yearOfBirth;
    }

    /**
     * 
     * @return
     */
    public Character getGender() {

        return gender;
    }

    /**
     * 
     * @return
     */
    public int getFamilyIndex() {

        return familyIndex;
    }

    /**
     * 
     * @param familyIndex
     */
    public void setFamilyIndex(int familyIndex) {

        this.familyIndex = familyIndex;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * 
     * @param leftChildName
     */
    public void setLeftChildName(String leftChildName) {

        this.leftChildName = leftChildName;
    }

    /**
     * 
     * @param rightChildName
     */
    public void setRightChildName(String rightChildName) {

        this.rightChildName = rightChildName;
    }

    /**
     * 
     * @param middleChildName
     */
    public void setMiddleChildName(String middleChildName) {

        this.middleChildName = middleChildName;
    }

    /**
     * 
     * @param parentOneName
     */
    public void setParentOneName(String parentOneName) {

        this.parentOneName = parentOneName;
    }

    /**
     * 
     * @param parentTwoName
     */
    public void setParentTwoName(String parentTwoName) {

        this.parentTwoName = parentTwoName;
    }

    /**
     * 
     * @param yearOfBirth
     */
    public void setYearOfBirth(Integer yearOfBirth) {

        this.yearOfBirth = yearOfBirth;
    }

    /**
     * 
     * @param gender
     */
    public void setGender(Character gender) {

        this.gender = gender;
    }

    /**
     * 
     * @param child
     */
    public void addChild(String child) {

        if (leftChildName == null) setLeftChildName(child);
        else if (middleChildName == null) setMiddleChildName(child);
        else setRightChildName(child);
    }

    /**
     * 
     */
    @Override
    public String toString() {

        return "Name <" + name + "> Year of Birth <" + yearOfBirth + "> Gender <" + gender + "> Parent One <" + parentOneName + "> Parent Two <" + parentTwoName + ">";
    }

    /**
     * 
     * @param person
     */
    public static void addPerson(Node person) {

        nodes.put(person.name, person);
    }

    /**
     * 
     * @param name
     * @return
     */
    public static Node getPerson(String name) {

        return nodes.get(name);
    }

    /**
     * 
     * @param name
     * @return
     */
    public static boolean isPerson(String name) {

        return nodes.containsKey(name);
    }

    /**
     * 
     * @return
     */
    public static int numberOfFamilies() {

        int num = 0;
        families.clear();

        for (String name: nodes.keySet()) {
            if (families.containsKey(name)) continue;
            
            setFamily(name, num++);
        }

        return num;
    }

    /**
     * 
     * @param name
     * @param familyIndex
     */
    private static void setFamily(String name, int familyIndex) {

        if (name == null || families.containsKey(name) || !nodes.containsKey(name)) return;

        families.put(name, familyIndex);
        nodes.get(name).setFamilyIndex(familyIndex);

        if (nodes.get(name).parentOneName != null) setFamily(nodes.get(name).parentOneName, familyIndex);
        if (nodes.get(name).parentTwoName != null) setFamily(nodes.get(name).parentTwoName, familyIndex);
        if (nodes.get(name).leftChildName != null) setFamily(nodes.get(name).leftChildName, familyIndex);
        if (nodes.get(name).middleChildName != null) setFamily(nodes.get(name).middleChildName, familyIndex);
        if (nodes.get(name).rightChildName != null) setFamily(nodes.get(name).rightChildName, familyIndex);
    }

    /**
     * 
     * @param name
     * @return
     */
    public static TreeSet<Node> getSiblings(String name) {

        TreeSet<Node> siblings = new TreeSet<>();

        Node parentOne = nodes.get(nodes.get(name).parentOneName);
        Node parentTwo = nodes.get(nodes.get(name).parentTwoName);

        if (parentOne != null) {
            if (parentOne.leftChildName != null && !parentOne.leftChildName.equals(name)) siblings.add(nodes.get(parentOne.leftChildName));
            if (parentOne.middleChildName != null && !parentOne.middleChildName.equals(name)) siblings.add(nodes.get(parentOne.middleChildName));
            if (parentOne.rightChildName != null && !parentOne.rightChildName.equals(name)) siblings.add(nodes.get(parentOne.rightChildName));
        }
        
        if (parentTwo != null) {
            if (parentTwo.leftChildName != null && !parentTwo.leftChildName.equals(name)) siblings.add(nodes.get(parentTwo.leftChildName));
            if (parentTwo.middleChildName != null && !parentTwo.middleChildName.equals(name)) siblings.add(nodes.get(parentTwo.middleChildName));
            if (parentTwo.rightChildName != null && !parentTwo.rightChildName.equals(name)) siblings.add(nodes.get(parentTwo.rightChildName));
        }
        
        return siblings;
    }

    /**
     * 
     * @param name
     * @return
     */
    public static int getFamilySize(String name) {
        return getFamilySize(root(nodes.get(name)));
    }

    /**
     * 
     * @param root
     * @return
     */
    private static int getFamilySize(Node root) {
        if (root == null) return 0;
        return 1 + getFamilySize(nodes.get(root.leftChildName)) + getFamilySize(nodes.get(root.middleChildName)) + getFamilySize(nodes.get(root.rightChildName));
    }
    
    /**
     * 
     * @param name
     * @return
     */
    public static TreeSet<Node> getFamily(String name) {

        Node root = root(nodes.get(name));
        TreeSet<Node> family = new TreeSet<>();

        ArrayList<Node> queue = new ArrayList<>();
        family.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            Node front = queue.remove(0);
            family.add(front);

            Node child = nodes.get(front.leftChildName);
            if (child != null && !child.name.equals("?")) queue.add(child);

            child = nodes.get(front.middleChildName);
            if (child != null && !child.name.equals("?")) queue.add(child);

            child = nodes.get(front.rightChildName);
            if (child != null && !child.name.equals("?")) queue.add(child);
        }

        return family;
    }

    /**
     * 
     * @param node
     * @return
     */
    private static Node root(Node node) {

        if (node == null) return new Node("NULL", 'N', 0, "", "");

        if (node.parentOneName.equals("?") && node.parentTwoName.equals("?")) return node;

        Node left = root(nodes.get(node.parentOneName));

        if (!left.name.equals("NULL")) return left;

        if (left.name.equals("NULL")) return root(nodes.get(node.parentTwoName));

        return new Node("NULL", 'N', 0, "", "");
    }

    /**
     * 
     */
    public static void printAll() {

        for (String key: nodes.keySet())
            System.out.println(nodes.get(key));
    }

    /**
     * 
     * @param personOne
     * @param personTwo
     * @return
     */
    public boolean isRelated(String personOne, String personTwo) {
        numberOfFamilies();
        return nodes.get(personOne).familyIndex == nodes.get(personTwo).familyIndex;
    }
    
    /**
     *
     */
    @Override
    public int compareTo(Node that) {
        return this.name.compareTo(that.name);
    }
    
}
