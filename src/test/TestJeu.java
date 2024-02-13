package test;

import arbre.Classification;
import arbre.DicTree;
import interfaceGraphique.MenuWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestJeu {

	public static void main(String[] args) throws Exception {

		// ajouter les mots au dictionnaire
		DicTree arbre = new DicTree("$");
		File file = new File("./test.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> wordsArray = new ArrayList<>();
		String st;
		Classification classification = new Classification();
		// Condition holds true till
		// there is character in a string

		// if (!arbre.wordExistsInFile("congrats", file.getPath()))
		// arbre.addWordToFile("congrats", file.getAbsolutePath());

		// arbre.deleteWordFromFile("congrats", file.getAbsolutePath());

		while ((st = br.readLine()) != null) {
			if (wordsArray.contains(st.toUpperCase()) || st.equals(""))
				continue;
			wordsArray.add(st.toUpperCase());
			// arbre.addWord(st);
		}
		Collections.sort(wordsArray);
		for (String string : wordsArray) {
			// System.out.println(string);
			arbre.addWord(string);
		}
		// arbre.drawTree();
		System.out.println(arbre.findNodeByValueReturnList("s").toString());
		ArrayList<String> arr = arbre.findNodeByValueReturnList("\0");
		// for (String string : arr) {
		// System.out.println(arbre.pathToWord(string));
		// }
		Map<String, Float> map = arbre.calculateDifficulty();
		System.out.println(map);
		System.out.println(map.size());
		classification.classify(map);
		classification.listsLength();
		System.err.println("#############");
		System.out.println(classification.randomWord(1));
		System.out.println(classification.randomWord(2));
		System.out.println(classification.randomWord(3));
		System.err.println("#############");
		// // choisir difficulté
		// System.out.println("Choose difficulty : (1) Easy / (2) Medium / (3) Hard
		// \n");
		// Scanner scanner = new Scanner(System.in);
		// int difficulty = scanner.nextInt();
		// int nbTries = 0;
		// // String wordToGuess = arbre.chooseWord(difficulty);
		// String str = "";

		// switch (difficulty) {
		// case 1:
		// nbTries = 10;
		// break;
		// case 2:
		// nbTries = 7;
		// break;
		// case 3:
		// nbTries = 4;
		// break;
		// default:
		// }
		// //

		String word = arbre.randomWord(arbre.getRoot());
		System.out.println(arbre.pathToWord(word));
		System.out.println(word);
		String[] wordArray = word.split("");
		//
		// Début du jeu

		// Construire l'interface graphique
		// JFrame frame = new JFrame("Jeu du Pendu");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuWindow penduPanel = new MenuWindow();
		// frame.add(penduPanel);
		// frame.pack();
		// frame.setLocationRelativeTo(null);
		// frame.setVisible(true);

		// L'utilisateur va commencer à jouer
		// System.out.println("Donner moi une lettre : ");
		// Scanner scanner = new Scanner(System.in);
		// Boolean correctGuess = false;
		// int etapeActuelle = penduPanel.getStep();
		// while (etapeActuelle < 9) {
		// String input = scanner.nextLine();
		// for (String string : wordArray) {
		// if (string.equals(input)) {
		// correctGuess = true;
		// break;
		// }
		// }
		// if (correctGuess)
		// continue;
		// etapeActuelle = penduPanel.getStep();
		// if (etapeActuelle < 11) {
		// penduPanel.setEtape(etapeActuelle + 1); // Augmente l'étape du pendu
		// } else {
		// break;
		// }
		// }
		// System.out.println("Le jeu est terminé !");
		// scanner.close();
	}
}
