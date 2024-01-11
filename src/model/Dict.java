package model;

import java.io.*;

public class Dict {
	Node root;

	public void fillDict() throws Exception {

		File file = new File("./test.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		// Condition holds true till
		// there is character in a string
		while ((st = br.readLine()) != null) {
			// Print the string
			// System.out.println(st);
			String[] dictArray = st.split("");
			for (String string : dictArray) {
				System.out.println("'" + string + "'");
				this.add(string);
			}
			System.out.println("\n");
		}
	}

	private Node addRecursive(Node current, String value) {
		if (current == null) {
			return new Node(value);
		} else {
			if (!current.value.equals(value)) {
				System.out.println("1");
				current.left = addRecursive(current.left, value);
			} else if (current.value.equals(value)) {
				System.out.println("2");
				current.right = addRecursive(current.right, value);
			}

			return current;
		}
	}

	public void add(String value) {
		Node rootInt = new Node();
		rootInt = root;
		if(rootInt != null)
		while(rootInt != null && !rootInt.value.equals(value)) {
			rootInt = rootInt.right;
		}
		root = addRecursive(rootInt, value);
	}

	public Dict createDict() {
		Dict dict = new Dict();

		return dict;
	}

	private void traversePreOrder(Node node) {
		if (node != null) {
			System.out.print(node.value);
			traversePreOrder(node.left);
			traversePreOrder(node.right);

		}
	}

	public void traversePreOrder() {
		traversePreOrder(root);
	}

	private void dessinerAvecContraintes(Node node, int niveau) {
		if (node == null) {
			return;
		}
		if (node.estFeuille()) {
			for (int i = 0; i < niveau; i++) {
				System.out.print("   ");
			}
			System.out.println("|_" + node.getValue());
			return;
		}

		if (node.getRight() != null) {
			dessinerAvecContraintes(node.getRight(), niveau + 1);
		}
		for (int i = 0; i < niveau; i++) {
			System.out.print("   ");
		}
		System.out.println("|_" + node.getValue());

		if (node.getLeft() != null) {
			dessinerAvecContraintes(node.getLeft(), niveau + 1);
		}
	}

	public void dessinerArbre() {
		System.out.println("_______ Votre arbre _________ \n\n");
		dessinerAvecContraintes(this.root, 0);
	}
}
