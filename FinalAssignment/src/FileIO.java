import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

    public final static FileIO fileIO = new FileIO();

    private FileIO() {

    }

    public ArrayList<Node> read(String filePath) throws IOException {

        ArrayList<Node> roots = new ArrayList<>();

        BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));
        
        String line = file.readLine();
        
        while (line != null) {
            String[] elements = line.split("\\s+");
            
            line = file.readLine();
        }
        
        return roots;
    }

}
