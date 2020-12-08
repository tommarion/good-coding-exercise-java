import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodingExercise {

    public static void main(String[] args){
        try {
            File inputFile = new File("input.csv");
            Scanner fileScanner = new Scanner(inputFile);
            
            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                FamilyTreeParser familyTreeParser = new FamilyTreeParser();
                Map<String, NodeData> familyTree = familyTreeParser.parseInputStr(input);
                familyTreeParser.printFamilyTree(familyTree, "0", 0);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR - unable to load file. Make sure input.csv exists in the same directory as the program");
            fnfe.printStackTrace();
        }
    }
}