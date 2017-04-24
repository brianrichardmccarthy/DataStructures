import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FileIO {

    public final static FileIO fileIO = new FileIO();

    private FileIO() {

    }

    /**
     * 
     * http://stackoverflow.com/questions/780541/how-to-sort-a-hashmap-in-java 
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public ArrayList<Node> read(String filePath) throws IOException {

        HashMap<Integer, ArrayList<Data>> elements = new HashMap<>();
        
        BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));
        
        String lines;

        while ((lines = file.readLine()) != null) {
            String[] element = lines.split("\\s+");
            
            Integer key = Integer.parseInt(element[2]);
            
            if (elements.containsKey(key)) elements.get(key).add(new Data(element[0], element[1].charAt(0), key, element[3], element[4]));
            else {
                elements.put(key, new ArrayList<>());
                elements.get(key).add(new Data(element[0], element[1].charAt(0), key, element[3], element[4]));
            }
            
        }
        
        file.close();

        ArrayList<Integer> keys = new ArrayList<>(elements.keySet());
        
        Collections.sort(keys);

        ArrayList<Node> roots = new ArrayList<>();
        
        for (Integer key : keys) {
            for (Data data : elements.get(key)) {
                if (data.getParentOne().equals("?") && data.getParentTwo().equals("?")) {
                    roots.add(new Node(data));
                    continue;
                }
                int result = 0;
                for (Node node : roots) {
                    int numOfParents = (((data.getParentOne().equals("?"))) ? 0 : 1) + (((data.getParentTwo().equals("?"))) ? 0 : 1);
                    result += Node.addChild(data, node);
                    if (result == numOfParents) {
                        result = 0;
                        break;
                    }
                }
            }
        }
        
        for (Node node : roots) {
            node.print(node, "");
            System.out.println();
        }
        
        return roots;
    }
    
    public static void main(String[] args) {
        try {
            fileIO.read("./src/small.txt");
        } catch (Exception e) {
            System.out.println("You done GOOFED");
        }
    }

}
