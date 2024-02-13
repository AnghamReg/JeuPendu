package arbre;

public class Node {
	private String value;
	private Node left, right;

	// constructeur
	Node() {
	}

	public Node(String c) {
		this.value = c;
		this.left = new Node();
		this.right = new Node();
	}

	// getters
	public String getValue() {
		return this.value;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	// setters
	public void setValue(String c) {
		this.value = c;
	}

	public void setLeft(Node fg) {
		this.left = fg;
	}

	public void setRight(Node fd) {
		this.right = fd;
	}

	// méthodes

	public boolean isLeaf() {
		return this.right == null && this.left == null;
	}

}
