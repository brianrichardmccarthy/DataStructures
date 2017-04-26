import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Contains instance data for each person
 *      Including (
 *       their name,
 *       their gender,
 *       their year of birth,
 *       both parents names,
 *       up to three children names,
 *       family index
 *      )
 * Plus static Hashmap of all people with their names used as keys, (Thus has the effect that the names must be unique)
 * @author Brian (unless referenced)
 *
 */
public class Node implements Comparable<Node> {

    /**
     * Class data
     */
    private static HashMap<String, Node> nodes = new HashMap<>();
    private static HashMap<String, Integer> families = new HashMap<>();

    /**
     * Intsance data
     */
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
     * Constructor - Parameters match that given in the file "large-database.txt"
     * 
     * @param name String
     * @param gender Character
     * @param year Integer
     * @param parentOne String
     * @param parentTwo String
     */
    public Node(String name, Character gender, Integer year, String parentOne, String parentTwo) {
        setName(name);
        setGender(gender);
        setYearOfBirth(year);
        setParentOneName(parentOne);
        setParentTwoName(parentTwo);
    }

    /**
     * Accessor for name
     * @return String
     */
    public String getName() {

        return name;
    }

    /**
     * Accessor for left child name
     * @return String
     */
    public String getLeftChildName() {

        return leftChildName;
    }

    /**
     * Accessor for right child
     * @return String
     */
    public String getRightChildName() {

        return rightChildName;
    }

    /**
     * Accessor for middle
     * @return String
     */
    public String getMiddleChildName() {

        return middleChildName;
    }

    /**
     * Accessor for parent one name
     * @return String
     */
    public String getParentOneName() {

        return parentOneName;
    }

    /**
     * Accessor for parent two name
     * @return String
     */
    public String getParentTwoName() {

        return parentTwoName;
    }

    /**
     * Accessor of year of birth
     * @return Integer
     */
    public Integer getYearOfBirth() {

        return yearOfBirth;
    }

    /**
     * Accessor for gender
     * @return Character
     */
    public Character getGender() {

        return gender;
    }

    /**
     * Accessor for family index
     * @return int
     */
    public int getFamilyIndex() {

        return familyIndex;
    }

    /**
     * Mutator for family Index
     * @param familyIndex int
     */
    public void setFamilyIndex(int familyIndex) {

        this.familyIndex = familyIndex;
    }

    /**
     * Mutator for name
     * @param name String
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Mutator for name
     * @param leftChildName String
     */
    public void setLeftChildName(String leftChildName) {

        this.leftChildName = leftChildName;
    }

    /**
     * Mutator for right child name
     * @param rightChildName String
     */
    public void setRightChildName(String rightChildName) {

        this.rightChildName = rightChildName;
    }

    /**
     * Mutator for middle child name
     * @param middleChildName String
     */
    public void setMiddleChildName(String middleChildName) {

        this.middleChildName = middleChildName;
    }

    /**
     * Mutator parent one name
     * @param parentOneName String
     */
    public void setParentOneName(String parentOneName) {

        this.parentOneName = parentOneName;
    }

    /**
     * Mutator for parent two name
     * @param parentTwoName String
     */
    public void setParentTwoName(String parentTwoName) {

        this.parentTwoName = parentTwoName;
    }

    /**
     * Mutator for year of birth
     * @param yearOfBirth Integer
     */
    public void setYearOfBirth(Integer yearOfBirth) {

        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Mutator for gender
     * @param gender Character
     */
    public void setGender(Character gender) {

        this.gender = gender;
    }

    /**
     * Mutator for all children. 
     * Checks if left is null then adds given child name to that, 
     * else if middle is mull add given child name to that, 
     * else add given name child to right child
     * @param child String
     */
    public void addChild(String child) {

        if (leftChildName == null) setLeftChildName(child);
        else if (middleChildName == null) setMiddleChildName(child);
        else setRightChildName(child);
    }

    /**
     * String representing this instances' data
     * @return String
     */
    @Override
    public String toString() {

        return "Name <" + name + "> Year of Birth <" + yearOfBirth + "> Gender <" + gender + "> Parent One <" + parentOneName + "> Parent Two <" + parentTwoName + ">";
    }

    /**
     * (Static) Method to add person to the hashmap of nodes
     * @param person Node
     */
    public static void addPerson(Node person) {

        nodes.put(person.name, person);
    }

    /**
     * (Static) Gets the Node object of the given name
     * @param name String
     * @return Node
     */
    public static Node getPerson(String name) {

        return nodes.get(name);
    }

    /**
     * Checks if the given name is in the hashmap
     * @param name String
     * @return boolean
     */
    public static boolean isPerson(String name) {

        return nodes.containsKey(name);
    }

    /**
     * Counts the number of family Nodes not connected to each other. 
     * Returns the number of family trees.
     * @return int
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
     * Sets the node object with the key names' family id to the given int value
     * @param name String
     * @param familyIndex int
     */
    private static void setFamily(String name, int familyIndex) {

        // prevent stackoverflow by returning if name is null or name is in families hash map or nodes does not contain the name
        if (name == null || families.containsKey(name) || !nodes.containsKey(name)) return;

        // add the name to the families hash map and update the nodes family index
        families.put(name, familyIndex);
        nodes.get(name).setFamilyIndex(familyIndex);

        // recursive search the two parent's and three children (only if they're not null)
        if (nodes.get(name).parentOneName != null) setFamily(nodes.get(name).parentOneName, familyIndex);
        if (nodes.get(name).parentTwoName != null) setFamily(nodes.get(name).parentTwoName, familyIndex);
        if (nodes.get(name).leftChildName != null) setFamily(nodes.get(name).leftChildName, familyIndex);
        if (nodes.get(name).middleChildName != null) setFamily(nodes.get(name).middleChildName, familyIndex);
        if (nodes.get(name).rightChildName != null) setFamily(nodes.get(name).rightChildName, familyIndex);
    }

    /**
     * Gets the siblings of the given name
     * @param name
     * @return
     */
    public static TreeSet<Node> getSiblings(String name) {

        // sibling tree set to be return
        TreeSet<Node> siblings = new TreeSet<>();

        // Get node of parent one
        Node parent = nodes.get(nodes.get(name).parentOneName);
        
        // if parent one exits
        if (parent != null) {
            // check left, middle, and right aren't null and don't match given name
            if (parent.leftChildName != null && !parent.leftChildName.equals(name)) siblings.add(nodes.get(parent.leftChildName));
            if (parent.middleChildName != null && !parent.middleChildName.equals(name)) siblings.add(nodes.get(parent.middleChildName));
            if (parent.rightChildName != null && !parent.rightChildName.equals(name)) siblings.add(nodes.get(parent.rightChildName));
        }
        
        // Get node of parent two
        parent = nodes.get(nodes.get(name).parentTwoName);
        
        // if parent two exits
        if (parent != null) {
            // check left, middle, and right aren't null and don't match given name
            if (parent.leftChildName != null && !parent.leftChildName.equals(name)) siblings.add(nodes.get(parent.leftChildName));
            if (parent.middleChildName != null && !parent.middleChildName.equals(name)) siblings.add(nodes.get(parent.middleChildName));
            if (parent.rightChildName != null && !parent.rightChildName.equals(name)) siblings.add(nodes.get(parent.rightChildName));
        }
        
        return siblings;
    }

    /**
     * Get the size of the family of the given family member
     * @param name String
     * @return int
     */
    public static int getFamilySize(String name) {
        return getFamilySize(root(nodes.get(name)));
    }

    /**
     * Recursively search given nodes children and keep adding 1 for every child that exist
     * Assumes given node is the root node
     * @param root Node
     * @return int
     */
    private static int getFamilySize(Node root) {
        if (root == null) return 0;
        return 1 + getFamilySize(nodes.get(root.leftChildName)) + getFamilySize(nodes.get(root.middleChildName)) + getFamilySize(nodes.get(root.rightChildName));
    }
    
    /**
     * Using level order traversal get all the family members of the given string starting at the root node
     * @param name String
     * @return TreeSet<Node>
     */
    public static TreeSet<Node> getFamily(String name) {

        // get root node
        Node root = root(nodes.get(name));
        
        // treeset to be returned, arraylist to be used as queue
        TreeSet<Node> family = new TreeSet<>();
        ArrayList<Node> queue = new ArrayList<>();
        
        // add root node to queue
        queue.add(root);

        // while queue has nodes
        while (!queue.isEmpty()) {
            
            // remove front node
            root = queue.remove(0);
            
            // add to family arraylist
            family.add(root);

            // get the left child
            Node child = nodes.get(root.leftChildName);
            
            // if left child exists and is known add to the queue
            if (child != null && !child.name.equals("?")) queue.add(child);

            // get the left child
            child = nodes.get(root.middleChildName);
            
            // if left child exists and is known add to the queue
            if (child != null && !child.name.equals("?")) queue.add(child);

            // get the left child
            child = nodes.get(root.rightChildName);
            
            // if left child exists and is known add to the queue
            if (child != null && !child.name.equals("?")) queue.add(child);
        }

        return family;
    }

    /**
     * Recursively move up the given nodes' parents until a node with both parents are unknown are found and return that node
     * @param node Node
     * @return Node
     */
    private static Node root(Node node) {

        // prevents stackoverflow
        if (node == null) return new Node("NULL", 'N', 0, "", "");

        // found root node
        if (node.parentOneName.equals("?") && node.parentTwoName.equals("?")) return node;

        // get first parent
        Node parent = root(nodes.get(node.parentOneName));

        // check if the root node has been found in recursive call, if yes return it
        if (!parent.name.equals("NULL")) return parent;

        // check if the root node has not been found in first parent side
        // then check parent two side of family
        if (parent.name.equals("NULL")) return root(nodes.get(node.parentTwoName));

        // return node not found
        return new Node("NULL", 'N', 0, "", "");
    }

    /**
     * Prints all the nodes in random order
     */
    public static void printAll() {

        for (String key: nodes.keySet())
            System.out.println(nodes.get(key));
    }

    /**
     * 
     * Returns true if the family index matches the both given people
     * 
     * @param personOne String
     * @param personTwo String
     * @return boolean
     */
    public static boolean isRelated(String personOne, String personTwo) {
        numberOfFamilies();
        return nodes.get(personOne).familyIndex == nodes.get(personTwo).familyIndex;
    }
    
    /**
     * Compares this nodes' name to other nodes' name
     * 
     * @param Node
     * @return int
     */
    @Override
    public int compareTo(Node that) {
        return this.name.compareTo(that.name);
    }
    
}
