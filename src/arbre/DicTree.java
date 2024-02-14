package arbre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DicTree {
	private static Node root;
	private static String path;

	// constructeur
	public DicTree(String c) {
		if (root == null) {
			// System.out.println("root created");
			DicTree.root = new Node(c);
		}
	}

	// getters
	public Node getRoot() {
		return DicTree.root;
	}

	// setters
	public void setRoot(String c) {
		DicTree.root.setValue(c);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// méthodes

	// arbre est vide
	public boolean isEmpty() {
		return DicTree.root.getValue().equals("$");
	}

	// dessiner arbre
	public void drawTree() {
		System.out.println("_________ Votre arbre ___________ \n\n");
		drawTreeWithLevel(this.root, 0);
	}

	private void drawTreeWithLevel(Node node, int level) {
		if (node == null) {
			return;
		}
		if (node.isLeaf()) {
			for (int i = 0; i < level; i++) {
				System.out.print("   ");
			}
			System.out.println("|_" + node.getValue());
			return;
		}

		if (node.getRight() != null) {
			drawTreeWithLevel(node.getRight(), level + 1);
		}
		for (int i = 0; i < level; i++) {
			System.out.print("   ");
		}
		System.out.println("|_" + node.getValue());

		if (node.getLeft() != null) {
			drawTreeWithLevel(node.getLeft(), level + 1);
		}
	}

	// taille
	public int sizeTree() {
		return sizeSubTree(this.root);
	}

	private int sizeSubTree(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + sizeSubTree(node.getLeft()) + sizeSubTree(node.getRight());
		}
	}

	// hauteur
	public int heightTree() {
		return heightSubTree(this.root);
	}

	private int heightSubTree(Node node) {
		if (node == null) {
			return 0;
		} else {
			return (1 + maximum(heightSubTree(node.getLeft()), heightSubTree(node.getRight())));
		}
	}

	private int maximum(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	// nombre de feuilles
	public int nbLeaf() {
		return calculLeaf(this.root);
	}

	private int calculLeaf(Node node) {
		if (node == null) {
			return 0;
		} else {
			if (node.isLeaf()) {
				return 1;
			} else {
				return (calculLeaf(node.getLeft()) + calculLeaf(node.getRight()));
			}
		}
	}

	// rechercher
	public boolean findNodeByValue(String c) {
		return findNode(this.root, c);
	}

	private boolean findNode(Node node, String c) {
		if (node == null) {
			return false;
		} else {
			if (node.getValue().equals(c)) {
				return true;
			} else {
				return (findNode(node.getRight(), c) || findNode(node.getLeft(), c));
			}
		}
	}

	// rechercher
	public ArrayList<String> findNodeByValueReturnList(String c) {
		ArrayList<String> arr = new ArrayList<String>();
		getArrayPath(this.root, c, "", arr);
		return arr;
	}

	// retourner le trajet pour un character
	private void getArrayPath(Node node, String c, String ch, ArrayList<String> arr) {
		if (node == null) {
			ch = "";
		} else {
			if (node.getValue() != null) {
				if (node.getValue().equals(c)) {
					arr.add(ch + "L");
				} else {
					getArrayPath(node.getRight(), c, ch + "R", arr);
					getArrayPath(node.getLeft(), c, ch + "L", arr);
				}
			} else {
				ch = "";
			}
		}
	}

	// insérer chaine dans arbre
	public void addWord(String ch) {
		int n = ch.length();
		String[] charArray = ch.split("");

		if (this.isEmpty()) {
			this.setRoot(charArray[0]);
			Node j = new Node();
			this.root.setLeft(new Node(charArray[1]));
			j = this.root.getLeft();
			int i = 2;
			while (i < n) {
				Node fg = new Node(charArray[i]);
				j.setLeft(fg);
				j = j.getLeft();
				i++;
			}
			j.setLeft(new Node("\0"));
			return;
		} else {
			// Premier lettre de la chaine est différent du racine
			if (!this.root.getValue().equals(charArray[0])) {
				if (this.root.getRight().getValue() == null) {

					this.root.setRight(new Node(charArray[0]));
					Node j = new Node();
					j = this.root.getRight();
					int i = 1;
					while (i < n) {
						Node fd = new Node("" + charArray[i]);
						j.setLeft(fd);
						j = j.getLeft();
						i++;
					}
					j.setLeft(new Node("\0"));
					return;
				} else {
					// Fils droite différent de null
					Node j = new Node();
					j = this.root.getRight();
					boolean stop = false;
					int a = 0;
					// parcourir l'arbre jusqu'à arrêter à \0
					// ou jusqu'à FD=null ou FG=null
					while (!stop) {
						if (j.getValue().equals("\0")) {
							stop = true;
						} else {
							if (j.getValue().equals(charArray[a])) {
								if (j.getLeft() == null) {
									stop = true;
								} else {
									j = j.getLeft();
									a++;
								}

							} else {
								if (j.getRight().getValue() == null) {
									stop = true;
								} else {
									j = j.getRight();
								}
							}
						}
					}
					ajouterChaineArbre(charArray, a, j, n);
				}
			} else {
				// premier lettre de la chaine est égale à la racine
				Node j = new Node();
				j = this.root.getLeft();
				boolean stop = false;
				int a = 1;
				// parcourir l'arbre jusqu'à arrêter à \0
				// ou jusqu'à FD=null ou FG=null
				while (!stop) {
					if (j.getValue().equals("\0")) {
						stop = true;
					} else {
						if (j.getValue().equals(charArray[a])) {
							if (j.getLeft() == null) {
								stop = true;
							} else {
								j = j.getLeft();
								a++;
							}

						} else {
							if (j.getRight().getValue() == null) {
								stop = true;
							} else {
								j = j.getRight();
							}
						}
					}
				}
				// ajouter le reste de la chaine à l'arbre
				ajouterChaineArbre(charArray, a, j, n);
			}
		}
	}

	private void ajouterChaineArbre(String[] charArray, int a, Node j, int length) {
		Node fd = new Node(charArray[a]);
		Node y = new Node();
		j.setRight(fd);
		y = j.getRight();
		int i = a + 1;
		while (i < length) {
			Node x = new Node("" + charArray[i]);
			y.setLeft(x);
			y = y.getLeft();
			i++;
		}
		y.setLeft(new Node("\0"));
	}

	// Transformer le trajet en un mot
	public String pathToWord(String wordPath) {
		Node rootInt = this.getRoot();
		String word = "";
		String[] paths = wordPath.split("");
		for (String string : paths) {
			if (string.equals("L")) {
				word = word.concat(rootInt.getValue());
				rootInt = rootInt.getLeft();
			} else if (string.equals("R")) {
				rootInt = rootInt.getRight();
			}
		}
		return word;
	}

	// Calculer la difficulté des mots
	public Map<String, Float> calculateDifficulty() {
		char[] LCL = { 'Z', 'Q', 'X', 'J', 'K', 'V', 'Y' },
				MCL = { 'E', 'T', 'N', 'A', 'O', 'I', 'S', 'H', 'R', 'D' };
		Map<String, Float> wordsDiff = new HashMap<>();
		float nLCL = 0, nMCL = 0;
		float diff = 0;
		ArrayList<String> arr = this.findNodeByValueReturnList("\0");
		for (String wordPath : arr) {
			nLCL = 0;
			nMCL = 0;
			String word = this.pathToWord(wordPath);
			if (word.length() > 1)
				word = word.substring(0, word.length() - 1);
			Set<Character> uniqueLetters = new HashSet<>();
			for (char c : word.toCharArray()) {
				uniqueLetters.add(c);
			}
			for (char c : uniqueLetters) {
				for (char char1 : LCL) {
					if (char1 == c)
						nLCL++;
				}
				for (char char2 : MCL) {
					if (char2 == c)
						nMCL++;
				}
			}
			diff = word.length() * (1 - (nLCL / word.length())) * (1 + (nMCL / word.length()));
			wordsDiff.put(word, diff / 10);
		}
		return wordsDiff;
	}

	// ajouter un mot au fichier
	public boolean addWordToFile(String word) {
		word = "\n" + word;
		try {
			Files.write(Paths.get(getPath()), word.toLowerCase().getBytes(), StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// verifier si le mot existe dans le fichier
	public boolean wordExistsInFile(String word) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.getPath()));
			String st;
			while ((st = br.readLine()) != null) {
				if (word.toLowerCase().equals(st.toLowerCase())) {
					return true;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// supprimer un mot du fichier
	public boolean deleteWordFromFile(String word) throws Exception {
		boolean success = false;
		try (BufferedReader br = new BufferedReader(new FileReader(path));
				BufferedWriter bw = new BufferedWriter(new FileWriter(path + ".tmp"))) {

			String line;
			while ((line = br.readLine()) != null) {
				if (!line.toUpperCase().contains(word.toUpperCase())) {
					bw.write(line + "\n");
				}

			}
			System.out.println("String deleted from the file");
			success = true;
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}

		File originalFile = new File(path);
		File tempFile = new File(path + ".tmp");

		try {
			Files.newBufferedWriter(Paths.get(path), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileChannel src = new FileInputStream(tempFile).getChannel();
		FileChannel dest = new FileOutputStream(originalFile).getChannel();
		dest.transferFrom(src, 0, src.size());

		if (tempFile.delete())
			System.out.println("File Deleted");
		else
			System.err.println("File not deleted");
		return success;
	}

}
