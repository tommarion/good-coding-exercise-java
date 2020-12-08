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
public class FamilyTreeParser {

	public FamilyTreeParser() {}

	/**
	 *
	 * This method parses a string value of the format 
	 *
	 * 		"parent_id,node_id,node_name|parent_id...",
	 * 
	 * and creates a hash map object to store the relational data.
	 *
	 * Using hash map allows for O(1) insert and get complexities. Simply
	 * adding the child id value to a parent node as we process them stores 
	 * our relation, and since get is O(1), our complexity stays at O(n).
	 *
	 * By storing "missed relationships" we are able to process the nodes
	 * in any order without increasing complexity.
	 *  
	 */
	public Map<Integer, NodeData> parseInputStr(String familyTreeStr) {

		// Stores our family tree relation data
 		Map<Integer, NodeData> familyTree = new HashMap<>();

 		// Stores parent/children relation when parent data has not been parsed yet
 		// This is only used when input data is not in ascending order by node_id
 		Map<Integer, List<Integer>> missedRelationships = new HashMap<>();

 		// Splits the string into nodes based on the pipe delimiter
        String[] nodes = familyTreeStr.split("\\|");
        for (String node : nodes) {
        	// Splits the node into data and creates an object with it
        	FamilyTreeNode familyTreeNode = new FamilyTreeNode(node.split(","));

        	Integer parentId = familyTreeNode.getNodeData().getParentId();
        	Integer nodeId = familyTreeNode.getNodeId();
        	familyTree.put(nodeId, familyTreeNode.getNodeData());

        	// Add children ids to this node if we've processed any before now
        	if (missedRelationships.containsKey(nodeId)) {
        		familyTree.get(nodeId).getChildrenIds().addAll(missedRelationships.get(nodeId));
        		missedRelationships.remove(nodeId);
        	}

        	// Store this node_id as the child of it's parent_id node
        	// Skip if root
        	if (parentId != null) {
        		// If the parent node has already been processed, add the child id
        		if (familyTree.containsKey(parentId)) {
        			familyTree.get(parentId).getChildrenIds().add(nodeId);
        		// If the parent node has NOT been processed yet, store it separately
        		} else {
        			// We already have child data for this parent node, add to it
        			if (missedRelationships.containsKey(parentId)) {
        				missedRelationships.get(parentId).add(nodeId);
        			// We have no data on this parent node, create a new entry
        			} else {
        				List<Integer> children = new ArrayList<>();
        				children.add(nodeId);
        				missedRelationships.put(parentId, children);
        			}
        		}
        	}
        }

        return familyTree;
	}

	/**
	 *
	 * This method takes the HashMap value constructed from the input string and recursively
	 * prints the name of each node, and then their children. Children nodes are directly under
	 * their parents with an extra indentation to indicate the newer generation.
	 *
	 * Since we are able to select our nodes by node_id with O(1) complexity, this method
	 * operates at O(n) efficiency.
	 *
	 */
	public void printFamilyTree(Map<Integer, NodeData> tree, Integer nodeId, int generation) {
		NodeData node = tree.get(nodeId);
		// Print the appropriate number of tabs for the generation we're on
		for (int i=0; i < generation; i++) {
			System.out.print("\t");
		}
		System.out.println(node);

		// If this node has children, recursively call this function for all of them
		if (!node.getChildrenIds().isEmpty()) {
			for (Integer childId : node.getChildrenIds()) {
				// Increase generation for children
				printFamilyTree(tree, childId, generation + 1);
			}
		}
	}
}