import copy

'''
Classes for General Tree structure to store and print
nodes with family tree relationships
'''
class TreeNode:
    def __init__(self, parent_node=None, data=None, children=None):
        self.parent = None
        self.data = data
        self.children = []

        if parent_node is not None:
        	self.parent = copy.deepcopy(parent_node)

        if children is not None:
            children.sort(key=get_node_id)
            for child in children:
                child.parent = self
                self.children.append(child)
            # Placeholder data node_id should be parent_id of children
            if self.data.node_id == None:
            	self.data.node_id = children[0].data.parent_id

    # Takes a list of node data lists and iterates through them creating
    # objects and then inserting them into the tree accordingly
    def insert_nodes(self, nodes_list):
        root_node = self
        missed_nodes = []

        # Add object to tree, and reset our 'pointer' to root
        for node_data in nodes_list:
            node = NodeData(node_data)
            if not search_and_insert(root_node, node):
                missed_nodes.append(node)
            while root_node.parent is not None:
                root_node = root_node.parent

        for node in missed_nodes:
            search_and_insert(root_node, node)
            while root_node.parent is not None:
                root_node = root_node.parent

        return root_node

    def print_tree(self):
    	print_tree(self, 0)

class NodeData:
	def __init__(self, node_data=None):
		self.parent_id = None
		self.node_id = None
		self.node_name = None
		if node_data is not None:
			self.parent_id = node_data[0]
			self.node_id = node_data[1]
			self.node_name = node_data[2]

# Recursive function to print tree in a Depth-First style using
#tabs to indicate levels 
def print_tree(node, tabs):
	padding = "\t" * tabs
	if node.data is not None:
		if node.data.node_name:
			print(padding + node.data.node_name)
		else:
			print(padding + "Missing Data....")
		for child in node.children:
			print_tree(child, tabs + 1)


# Recursive function to search through nodes for a match based on
#parent_id and node_id of current 'pointer' index and insert object
def search_and_insert(current_node, insert_node):
	# Case 1: root is null
	#Set root to the new data
	if current_node.data == None:
		current_node.data = insert_node
		return True

	# Case 2: node_id is equal to the parent_id of our new data
	#Add new data to children of this node
	if current_node.data.node_id == insert_node.parent_id:
		current_node.children.append(TreeNode(current_node, insert_node))
		current_node.children.sort(key=get_node_id)
		return True

	# Case 3: parent_id is equal to the node_id of our new data
	#Add this node as a child of this new data
	if current_node.data.parent_id == insert_node.node_id:
		current_node = TreeNode(None, insert_node, [current_node])
		return True

	# Case 4: parent_id is equal to the parent_id of our new data
	#Add this node and this new data as children of a new, blank node
	if current_node.data.parent_id == insert_node.parent_id:
		current_node = TreeNode(None, NodeData(), [ current_node, TreeNode(current_node, insert_node) ])
		return True

	# Case 5: node_id's match
	#We created a placeholder node to keep structure, now we fill in the data
	if current_node.data.node_id == insert_node.node_id:
		current_node.data = insert_node
		return True


	# Case 6: this node is not the parent but has children
	#Check if any of this node's children is the parent
	if len(current_node.children) is not 0:
		for child in current_node.children:
			if search_and_insert(child, insert_node):
				return True

    # Case 7: there are no elements related at all...
	return False


def get_node_id(e):
	return e.data.node_id



