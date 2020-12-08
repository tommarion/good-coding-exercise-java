public class FamilyTreeNode {
	public Integer nodeId;
	public NodeData nodeData;

    public FamilyTreeNode(String[] nodeStr) {
        this.nodeId = Integer.valueOf(nodeStr[1]);
    	this.nodeData = new NodeData(nodeStr[0], nodeStr[2]);
    }

    public Integer getNodeId() {
    	return this.nodeId;
    }
    public NodeData getNodeData(){
    	return this.nodeData;
    }
}