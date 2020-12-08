import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CodingExercise {

    public static void main(String[] args){

        FamilyTreeParser familyTreeParser = new FamilyTreeParser();
        MemoryEfficientTreeParser memoryEfficientTreeParser = new MemoryEfficientTreeParser();
        try {
            File inputFile = new File("input.txt");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] chunk = new byte[1024];
            int chunkLength = 0;
            while ((chunkLength = inputStream.read(chunk)) != -1) {
                String chunkStr = new String(chunk, StandardCharsets.UTF_8);
                memoryEfficientTreeParser.parseAndPrintFamilyTree(chunkStr);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR - unable to load file. Make sure input.txt exists in the same directory as the program");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("ERROR - unable to read from file");
            ioe.printStackTrace();
        }
    }
}