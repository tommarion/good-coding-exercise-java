import java.util.ArrayList;
import java.util.List;

public class NodeData {
	public Integer parentId;
	public String nodeName;
	public List<Integer> childrenIds;

    public NodeData(String parentId, String nodeName) {
    	if (parentId.equals("null")) {
    		this.parentId = null;
    	} else {
	    	this.parentId = Integer.valueOf(parentId);
    	}
    	this.nodeName = nodeName;
    	this.childrenIds = new ArrayList<>();
    }

    @Override
    public String toString() {
    	return this.nodeName;
    }

    public Integer getParentId() {
    	return this.parentId;
    }

    public List<Integer> getChildrenIds() {
    	return this.childrenIds;
    }
}