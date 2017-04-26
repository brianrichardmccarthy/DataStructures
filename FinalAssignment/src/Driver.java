import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * @author Brian
 *
 */
public class Driver {

    Scanner numberInput;
    Scanner stringInput;

    /**
     * Constructor
     */
    public Driver() {
        numberInput = new Scanner(System.in);
        stringInput = new Scanner(System.in);

        System.out.println("Plaese enter file path and name and extension");
        FileIO.instance.read(stringInput.nextLine());

        menu();
    }

    /**
     * 
     */
    private void menu() {

        int choice = 0;

        do {
            System.out.println("1) Add a person");
            System.out.println("2) Find a family");
            System.out.println("3) Get the size of a family");
            System.out.println("4) Check if two people are related");
            System.out.println("5) Find a Person");
            System.out.println("6) Get a Persons' siblings");
            System.out.println("7) Get the total number of familes");
            System.out.println("8) Print all the families");
            System.out.println("0) Quit");
            System.out.print("Choice: ");
            choice = numberInput.nextInt();
            System.out.println("");

            switch (choice) {
                case 0:
                    break;
                case 1:
                    addPerson();
                    break;
                case 2:
                    findFamily();
                    break;
                case 3:
                    getFamilySize();
                    break;
                case 4:
                    checkRelated();
                    break;
                case 5:
                    findPerson();
                    break;
                case 6:
                    findSiblings();
                    break;
                case 7:
                    totalFamilies();
                    break;
                case 8:
                    printAll();
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }

        } while (choice != 0);

    }

    /**
     * 
     */
    private void addPerson() {

        System.out.println("Enter first name");
        String name = stringInput.nextLine();
        System.out.println("Enter F or M");
        char gender = stringInput.nextLine().charAt(0);
        System.out.println("Enter year");
        int year = numberInput.nextInt();
        System.out.println("Enter 1st parent first name");
        String parentOne = stringInput.nextLine();
        System.out.println("Enter 2nd parent first name");
        String parentTwo = stringInput.nextLine();

        if (Node.isPerson(name)) {
            System.out.println("Sorry this person already exist");
            return;
        }

        Node.addPerson(new Node(name, gender, year, parentOne, parentTwo));
        Node parent = Node.getPerson(parentOne);

        if (parent != null) {
            if (parent.getLeftChildName() == null || parent.getLeftChildName().equals("?")) parent.setLeftChildName(
                name);
            else if (parent.getMiddleChildName() == null || parent.getMiddleChildName().equals(
                "?")) parent.setMiddleChildName(name);
            else parent.setRightChildName(name);
        }

        parent = Node.getPerson(parentTwo);

        if (parent != null) {
            if (parent.getLeftChildName() == null || parent.getLeftChildName().equals("?")) parent.setLeftChildName(
                name);
            else if (parent.getMiddleChildName() == null || parent.getMiddleChildName().equals(
                "?")) parent.setMiddleChildName(name);
            else parent.setRightChildName(name);
        }
        
        System.out.println("\n");
    }

    /**
     * 
     */
    private void findFamily() {
        System.out.println("Enter the name of a family member");
        TreeSet<Node> family = Node.getFamily(stringInput.nextLine());
        
        System.out.println("\n");
        
        for (Node person : family) System.out.println(person);
        
        System.out.println("\n");
        
    }
    
    /**
     * 
     */
    private void getFamilySize() {
        System.out.println("Enter family member name");
        System.out.println("Family size <" + Node.getFamilySize(stringInput.nextLine()) + ">\n");
    }
    
    /**
     * 
     */
    private void checkRelated() {
        System.out.println("Enter first person name");
        String personOne = stringInput.nextLine();
        System.out.println("Enter second person name");
        String personTwo = stringInput.nextLine();
        
        System.out.println((Node.isRelated(personOne, personTwo) ? personOne + " is related to " + personTwo : personOne + " isn't related to " + personTwo));
    }
    
    /**
     * 
     */
    private void findPerson() {
        System.out.println("Enter a name");
        
        String name = stringInput.nextLine();
        Node temp = Node.getPerson(name);
        
        if (temp != null) System.out.println(temp);
        else System.out.println("Sorry, " + name + " is not in any tree");
    }
    
    /**
     * 
     */
    private void findSiblings() {
        System.out.println("Enter a name");
        TreeSet<Node> sibslings = Node.getSiblings(stringInput.nextLine());
        for (Node sibling : sibslings) System.out.println(sibling);
    }
    
    /**
     * 
     */
    private void totalFamilies() {
        System.out.println("Total number of families <" + Node.numberOfFamilies() + ">");
    }
    
    /**
     * 
     */
    private void printAll() {
        Node.printAll();
        System.out.println();
    }
    
    public static void main(String[] args) {

        new Driver();
    }

}
