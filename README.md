This program was updated to emphasize efficiency on Dec 7 2020 as part of
a coding exercise for Lumen.

#							Instructions
To run this program, make sure Java is installed. First navigate to the
directory containing the program. Then compile using the command:

	javac *.java

Once compiled, run the program by using the command:

	java CodingExercise


#							Parsing

Using HashMap as a data structure allows for O(1) insert and get
operations. When parsing the string, it is first broken into CSV "nodes"
based on the pipe delimiter character "|". These CSV nodes are split into
data objects and added into the HashMap object. The parent node is then
selected, and updated to include its child node id.

DUE TO LIMITED INSTRUCTION
	- I did NOT assume that the input data would be in ascending order
	by node_id
	- I did NOT assume the input data would be more than one line in
	length
	- I did NOT assume the input data would be more too long for scanner
	to handle

This program CAN BE MODIFIED to handle any of these scenarios if
necessary.

To be able to accept nodes in any order, when a node is processed before
its parent, the parent-child relationship data is stored in a separate
HashMap. When new nodes are processed, a check is done to see whether
child data has been stored there, and if so, includes that data in the
tree node and removes it from the separate HashMap. This is also done in
O(1) complexity, which makes the overall function operate at O(n).

#							Printing

Printing the tree is as simple as printing each node recursively,
starting with the root and then respective children. Since a get 
operation on a HashMap is O(1) complexity, printing the entire tree is
done at O(n).
