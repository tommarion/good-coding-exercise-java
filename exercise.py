import tree

'''
This project reads a CSV file and constructs a Tree-like data structure
to store relations of each "Node" created from the input data.

Input:
Place a file named "input.csv" in the same directory as the program.
Expected format - parent_id,node_id,node_name|parent_id,node_id,node_name...
'''

#Initialize tree object
root = tree.TreeNode()

#Read input file
input_data = open('input.csv', 'r')

#Split file into rows using pipe delimiter
nodes_list = list(input_data)[0].split('|')

#Create list with lists of values
nodes_data_list = []
for node_data in nodes_list:
	nodes_data_list.append(node_data.split(','))

#Pass list of node values to insert function and print
root = root.insert_nodes(nodes_data_list)
root.print_tree()
