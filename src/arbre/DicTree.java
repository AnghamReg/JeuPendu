package arbre;

import java.util.ArrayList;
import java.util.Random;

public class DicTree {
	private Node root;

	// constructeur
	public DicTree(String c) {
		this.root = new Node(c);
	}

	// getters
	public Node getRoot() {
		return this.root;
	}

	// setters
	public void setRoot(String c) {
		this.root.setValue(c);
	}

	// méthodes

	// arbre est vide
	public boolean isEmpty() {
		return this.root.getValue().equals("$");
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
			ArrayList<String> arr=new ArrayList<String>();
			 getArrayPath(this.root, c,"",arr);
			return arr;
		}

		private void getArrayPath(Node node, String c,String ch,ArrayList<String> arr) {
			if (node == null) {
				 ch="";
			} else {
				if(node.getValue()!=null) {
					if (node.getValue().equals(c)) {
						 arr.add(ch+"L");
					} else {
						findNode(node.getRight(),c,ch+"R",arr);
						findNode(node.getLeft(), c,ch+"L",arr);
					}	
				}else {
					ch="";
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

	// retourne phrase
	public String chooseWord(int difficulty) {
		Random rand = new Random();
		int length = 0;
		switch (difficulty) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			default:
		}
		return "";
	}

	// fonction retourne path a partie de la racine exemple :LRRRLR
	//
	public String randomWord(Node root) {
		String word = "";
		// Intermediary var for root
		Node rootInt = root;
		while ((rootInt != null) && (rootInt.getValue() != "\0") && (rootInt.getValue() != null)) {
			// if there's no more letters on the right, force going left
			if ((rootInt.getLeft().getValue() != null) && (rootInt.getRight().getValue() == null)) {
				// System.out.println("1");
				// word = word.concat(rootInt.getValue());
				word = word.concat("L");
				rootInt = rootInt.getLeft();
				continue;
			}
			/*
			 * else if ((rootInt.getLeft() == null) && (rootInt.getRight() != null)) {
			 * rootInt = rootInt.getRight();
			 * break;
			 * }
			 */
			int rand = (int) Math.round(Math.random());
			if (rand == 0) {
				// System.out.println("2");
				// word = word.concat(rootInt.getValue());
				word = word.concat("L");
				rootInt = rootInt.getLeft();
			} else if (rand == 1) {
				// System.out.println("3");
				word = word.concat("R");
				rootInt = rootInt.getRight();
			}
		}
		return word;
	}

}
