package model;

public class Node {
	String value;
	Node left;
	Node right;
	
	Node(){
		
	}
	
	Node(String value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public boolean estFeuille() {
		return this.left==null && this.right==null;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
}
