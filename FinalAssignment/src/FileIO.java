import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * 
 * @author Brian
 *
 */
public class FileIO {

    public static final FileIO instance = new FileIO();

    /**
     * Private constructor for singleton
     */
    private FileIO() {
    }

    /**
     * Reads in the given file.
     * Expectes the file to have the format "Colby M 1600 ? ?" on each line else a Exception is thrown
     * @param filePath
     */
    public void read(String filePath) {

        try {
            BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));
            
            // track unlinked parents
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

                /*
                 * try to link parent to child now by
                 * first checking parent one is known
                 *      if yes then
                 *          if the parent is in the Node hash map link it with new node by adding its' child (current line/ Node)
                 *          else store it in the TreeSet
                 * repeat for parent 2
                 */
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
            
            /*
             * Loop through all child names that were added before their parents
             * Get the child node node.
             * check that parent 1 is known
             *      if yes add child name to parent node
             * repeat for parent two
             */
            for (String child : unsetParent) {
                Node childNode = Node.getPerson(child);

                if (!childNode.getParentOneName().equals("?")) Node.getPerson(childNode.getParentOneName()).addChild(
                    child);

                if (!childNode.getParentTwoName().equals("?")) Node.getPerson(childNode.getParentTwoName()).addChild(
                    child);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
