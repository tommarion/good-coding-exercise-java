import java.util.ArrayList;
import java.util.List;

public class NodeData {
	public String parentId;
	public String nodeName;
	public List<String> childrenIds;

    public NodeData(String parentId, String nodeName) {
    	if (parentId.equals("null")) {
    		this.parentId = null;
    	} else {
	    	this.parentId = parentId;
    	}
    	this.nodeName = nodeName;
    	this.childrenIds = new ArrayList<>();
    }

    @Override
    public String toString() {
    	return this.nodeName;
    }

    public String getParentId() {
    	return this.parentId;
    }

    public List<String> getChildrenIds() {
    	return this.childrenIds;
    }
}