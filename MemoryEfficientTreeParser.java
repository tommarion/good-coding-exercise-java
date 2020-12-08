import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * This class contains methods that create a hash map object to store
 * the relational data, and prints the data in an easily-readable format.
 *
 * Overall, both parsing and printing the tree are O(n) since each insert
 * and get is O(1)
 *  
 */
public class MemoryEfficientTreeParser {

	public MemoryEfficientTreeParser() {}

	/**
	 *
	 * This method parses a string value of the format 
	 *
	 * 		"parent_id,node_id,node_name|parent_id...",
	 * 
	 * assuming that the data is ordered by node_id, t
	 *  
	 */
	public void parseAndPrintFamilyTree(String inputStr) {
		Map<Integer, String> parentNames = new HashMap<>();
		List<Integer> currentGenIds = new ArrayList<>();

		int generation = 0;

		String[] nodes = inputStr.split("\\|");
		for (String node : nodes) {
			String[] nodeData = node.split(",");
			boolean isNewGeneration = currentGenIds.size() == 0 || 
				currentGenIds.contains(Integer.valueOf(nodeData[0]));

			if (isNewGeneration) {
				if (generation > 0) {
					System.out.println("\n");
				}
				System.out.print("Generation " + String.valueOf(generation++));
				currentGenIds.clear();
			}
			System.out.println();

			// if this node has a parent, print the name
			if (!nodeData[0].equals("null")) {
				System.out.print(parentNames.get(Integer.valueOf(nodeData[0])) + "'s child: ");
			}
			System.out.print(nodeData[2]);

			parentNames.put(Integer.valueOf(nodeData[1]), nodeData[2]);
			currentGenIds.add(Integer.valueOf(nodeData[1]));
		}
		System.out.println();
	}
}

