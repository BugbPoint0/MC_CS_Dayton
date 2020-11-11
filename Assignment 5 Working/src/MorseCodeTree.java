import java.util.ArrayList;

/**
 * @author David Dayton
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	TreeNode<String> root; // The root of the tree
	
	/**
	 * Constructor which builds the MorseCodeTree
	 */
	MorseCodeTree() {
		root = null;
		buildTree();
	}
	
	/**
	 * @Override
	 * Returns a reference to the root
	 * @return reference to root
	 */
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * @Override
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}

	/**
	 * Adds letter to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * @Override
	 * @param code the code for the new node to be added
	 * @return the linkedConverterTree with the new node added
	 */
	public MorseCodeTree insert(String code, String letter) {
		
		if (getRoot() == null) {
			setRoot(new TreeNode<String>(letter));
		} else {
			addNode(getRoot(), code, letter);
		}
		
		return this;
	}

	/**
	 * This is a recursive method that adds element to the correct position in the tree based on the code. A '.' (dot) means
	 *  traverse to the left. A "-" (dash) means traverse to the right. The code ".-" would be stored as the right child of the 
	 *  left child of the root Algorithm for the recursive method:
		1. if there is only one character
		a. if the character is '.' (dot) store to the left of the current root
		b. if the character is "-" (dash) store to the right of the current root
		c. return
		2. if there is more than one character
		a. if the first character is "." (dot) new root becomes the left child
		b. if the first character is "-" (dash) new root becomes the right child
		c. new code becomes all the remaining charcters in the code (beyond the first character)
		d. call addNode(new root, new code, letter)
	 * @Override
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		if (code.length() == 1) {
			if (code.equals(".")) {
				root.left = new TreeNode<String>(letter);
			} else {
				root.right = new TreeNode<String>(letter);
			}
		} else {
			if (code.charAt(0) == '.') {
				addNode(root.left, code.substring(1), letter);
				
			} else {
				addNode(root.right, code.substring(1), letter);
			}
		}
	}

	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * @Override 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	public String fetch(String code) {
		
		return fetchNode(getRoot(), code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * @Override
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		
		String data = null;
		
		if (code.length() == 1) {
			if (code.equals(".")) {
				data = root.left.getData();
			} else {
				data = root.right.getData();
			}
		} else {
			if (code.charAt(0) == '.') {
				return fetchNode(root.left, code.substring(1));
				
				
			} else {
				return fetchNode(root.right, code.substring(1));
				
			}
		}
		
		return data;
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @Override
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * @Override
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();	
	}

	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * @Override
	 */
	public void buildTree() {
		// level 0
		setRoot(new TreeNode<String>("")); 
		// level 1
		insert(".", "e"); insert("-", "t");
		// level 2 
		insert("..", "i"); insert(".-", "a"); insert("-.", "n"); insert("--", "m");
		// level 3 
		insert("...", "s"); insert("..-", "u"); insert(".-.", "r"); insert(".--", "w"); insert("-..", "d"); insert("-.-", "k");
		insert("--.", "g"); insert("---", "o");
		// level 4
		insert("....", "h"); insert("...-", "v"); insert("..-.", "f"); insert(".-..", "l"); insert(".--.", "p"); insert(".---", "j");
		insert("-...", "b"); insert("-..-", "x"); insert("-.-.", "c"); insert("-.--", "y"); insert("--..", "z"); insert("--.-", "q");
	}

	/**
	 * @Override
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> LNR = new ArrayList<String>();
		LNRoutputTraversal(getRoot(), LNR);
		return LNR;
	}

	/**
	 * @Override
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<String> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if (root == null) {
			return;
		}
		LNRoutputTraversal(root.left, list);
		list.add(root.getData());
		LNRoutputTraversal(root.right, list);
	}

}
