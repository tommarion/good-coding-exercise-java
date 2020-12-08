public class FamilyTreeNode {
	public String nodeId;
	public NodeData nodeData;

    public FamilyTreeNode(String[] nodeStr) {
        this.nodeId = nodeStr[1];
    	this.nodeData = new NodeData(nodeStr[0], nodeStr[2]);
    }

    public String getNodeId() {
    	return this.nodeId;
    }
    public NodeData getNodeData(){
    	return this.nodeData;
    }
}