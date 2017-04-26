import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class FileIO {

    public static final FileIO instance = new FileIO();

    /**
     * 
     */
    private FileIO() {
    }

    public void write(String fileNameAndPath) {
        
    }
    
    /**
     * 
     * @param filePath
     */
    public void read(String filePath) {

        
        try {
            BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));
            TreeSet<String> unsetParent = new TreeSet<>();
            String lines;
            while ( (lines = file.readLine()) != null) {
                String[] split = lines.split("\\s+");
                if (split.length != 5) {
                    file.close();
                    throw new Exception(
                        "Line should have the format\n\t<Name>_<Gender>_<Year>_<Parent Name>_<Parent Name>\nwhere _ is space\nInstead recieved line <" + lines + ">");
                }

                Node.addPerson(new Node(split[0], split[1].charAt(0), Integer.parseInt(split[2]), split[3], split[4]));

                if (!split[3].equals("?")) {
                    if (Node.isPerson(split[3])) Node.getPerson(split[3]).addChild(split[0]);
                    else unsetParent.add(split[0]);
                }

                if (!split[4].equals("?")) {
                    if (Node.isPerson(split[4])) Node.getPerson(split[4]).addChild(split[0]);
                    else unsetParent.add(split[0]);
                }
            }

            file.close();
            
            for (String child : unsetParent) {
                Node childNode = Node.getPerson(child);

                if (!childNode.getParentOneName().equals("?")) Node.getPerson(childNode.getParentOneName()).addChild(
                    child);

                if (!childNode.getParentTwoName().equals("?")) Node.getPerson(childNode.getParentTwoName()).addChild(
                    child);

            }

            System.out.println(Node.getSiblings("Mariam"));
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        instance.read("./src/small.txt");
    }
    
}
