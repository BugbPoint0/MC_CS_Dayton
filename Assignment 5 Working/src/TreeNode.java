/**
 * @author David Dayton
 * @param <T> Generic type for TreeNode
 */
public class TreeNode<T> {
	
	T data;
	TreeNode<T> left;
	TreeNode<T> right;
	
	/**
	 * Regular constructor for making a new Tree Node
	 * @param dataNode Data to set this nodes data variable to be
	 */
	public TreeNode(T dataNode) {
		left = null;
		right = null;
		data = dataNode;
	}
	
	/**
	 * A copy constructor for deep copies
	 * @param node A new TreeNode to be copied
	 */
	public TreeNode(TreeNode<T> node) {
		this.left = node.left;
		this.right = node.right;
		this.data = node.getData();
	}
	
	/**
	 * Gets this TreeNodes data variable
	 * @return data 
	 */
	public T getData() {
		return data;
	}
	
}

