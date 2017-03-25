
/**
 * @author Brian McCarthy, 20063914
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Driver {

    private StringBuilder sentence;
    private String outputFIle;
    private HashMap<String, DataItem> characters;
    private static final String HEX = "00001100101011011101000010011001";

    /**
     * Reads in given file to compress
     * 
     * @param file
     *            (File) input file
     * @param outputFile
     *            (String) output file name, path and extension
     */
    public Driver(File file, String outputFile) {

        try {
            double startTime = System.currentTimeMillis();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder data = new StringBuilder("");
            String nextLine;
            while ( (nextLine = reader.readLine()) != null) {
                data.append(nextLine);
            }
            reader.close();
            double currentTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Reading in the file <" + currentTime + ">");
            init(data, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Compresses given StringBuilder and outputs it to the given file
     * 
     * @param sentence
     *            (StringBuilder)
     * @param outputFile
     *            (String) output file name, path and extension
     */
    public Driver(StringBuilder sentence, String outputFile) {

        init(sentence, outputFile);
    }

    /**
     * Loops through the sentence and counts each charaters' occurence
     * @param sentence
     * @param outputFile
     */
    private void init(StringBuilder sentence, String outputFile) {

        outputFIle = outputFile;
        this.sentence = sentence;
        characters = new HashMap<>();
        double startTime = System.currentTimeMillis();
        for (int x = 0; x < sentence.length(); x++) {
            if (characters.containsKey(String.valueOf(this.sentence.charAt(x)))) characters.get(String.valueOf(
                this.sentence.charAt(x))).setOccurence();
            else characters.put(String.valueOf(this.sentence.charAt(x)), new DataItem(String.valueOf(
                this.sentence.charAt(x))));
        }
        double currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Building the array list of leaf nodes <" + currentTime + ">");

        binaryTree();
        encode();
    }

    /**
     * Builds up a binary tree of nodes where each leaf node is a charater in the file or string.
     * Then traverses the tree to get each leaf nodes' binary value depending on its position in the tree.
     */
    private void binaryTree() {

        ArrayList<DataItem> tempList = new ArrayList<>(characters.values());
        Collections.sort(tempList, DataItem.compareToNumber);

        double startTime = System.currentTimeMillis();
        Node root = new Node();
        setNode(root, new Node(tempList.get(0)), new Node(tempList.get(1)));

        for (int x = 2; x < tempList.size(); x++) {
            Node parent = new Node();
            if (x + 1 < tempList.size() && root.getThisItem().getOccurence() > tempList.get(x).getOccurence()) {
                Node temp = new Node();
                setNode(temp, new Node(tempList.get(x++)), new Node(tempList.get(x)));
                setNode(parent, root, temp);
            } else setNode(parent, new Node(tempList.get(x)), root);

            root = parent;
        }

        double currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Building the binary tree time <" + currentTime + ">");

        startTime = System.currentTimeMillis();
        root.setBinaryValue(root, "");
        currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Setting the binary value <" + currentTime + ">");
        // Node.printPreOrder(root, "");
    }

    /**
     * Encodes the String/ file to compressed String
     */
    private void encode() {

        double startTime = System.currentTimeMillis();
        StringBuilder encoded = new StringBuilder("");

        for (int x = 0; x < sentence.length(); x++) {
            encoded.append(characters.get(String.valueOf(this.sentence.charAt(x))).getBinary());
        }

        double currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Encodeding the String <" + currentTime + ">");

        startTime = System.currentTimeMillis();
        write(encoded.toString());
        currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Writing to file <" + currentTime + ">");
    }

    /**
     * Writes the compressed string to the given file (String from the constructor)
     * @param data (String)
     */
    private void write(String data) {

        try {
            /* FileOutputStream out = new FileOutputStream(outputFIle);
            
            int current = 4, previous = 0;
            
            while (current <= HEX.length()) {
                System.out.println("" + Integer.toBinaryString(Integer.parseInt(HEX.substring(previous, current), 2)));
                out.write(Integer.toBinaryString(Integer.parseInt(HEX.substring(previous, current), 2)));
                previous = current;
                current += 4;
            }
            
            out.close(); */

            int current = 8, previous = 0;

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFIle)));

            while (current < data.length()) {
                System.out.println("Current <" + current + ">");
                writer.write(Integer.toBinaryString(Integer.parseInt(HEX.substring(previous, current), 2)));
                previous = current;
                current += 8;
            }

            current = Math.abs(data.length() - (1 + current));
            writer.write(Integer.toBinaryString(Integer.parseInt(HEX.substring(previous, current), 2)));

            writer.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the parents' children to the left and right child
     * Sets the parents' data to its' left childs' data plus its' right childs' data
     * Sets the parents' occurrence to its' left childs' occurence plus its' right childs' occurence
     * @param parent (Node)
     * @param left (Node)
     * @param right (Node)
     */
    private void setNode(Node parent, Node left, Node right) {

        parent.setRightChild(left);
        parent.setLeftChild(right);
        parent.setThisItem(new DataItem(
            parent.getLeftChild().getThisItem().getData() + parent.getRightChild().getThisItem().getData()));
        parent.getThisItem().setOccurence(
            parent.getLeftChild().getThisItem().getOccurence() + parent.getRightChild().getThisItem().getOccurence());
    }

    public static void main(String[] args) {

        // new Driver(new File("./src/test.txt"), "./src/testCompressed.dat");
        new Driver(new StringBuilder("GO GO GOPHERS"), "./src/gophersCompressed.dat");
        System.out.println("Done");
        // System.out.println("\n\n");
        // new Driver(new StringBuilder("MISSISSIPPI RIVER"), "./src/mississippiCompressed.txt");
    }

}
