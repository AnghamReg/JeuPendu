package test;

import javax.swing.*;

import arbre.DicTree;
import arbre.Node;
import interfaceGraphique.HangManGraphic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class TestJeu {

	public static void main(String[] args) throws Exception {

		// ajouter les mots au dictionnaire
		DicTree arbre = new DicTree("$");
		File file = new File("./test.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		// Condition holds true till
		// there is character in a string
		while ((st = br.readLine()) != null) {
			arbre.addWord(st);
		}
		arbre.drawTree();

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
		System.out.println(arbre.pathToWord(arbre.getRoot(), word));
		System.out.println(word);
		String[] wordArray = word.split("");
		//
		// Début du jeu

		// Construire l'interface graphique
		JFrame frame = new JFrame("Jeu du Pendu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HangManGraphic penduPanel = new HangManGraphic();
		frame.add(penduPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// L'utilisateur va commencer à jouer
		System.out.println("Donner moi une lettre : ");
		Scanner scanner = new Scanner(System.in);
		Boolean correctGuess = false;
		int etapeActuelle = penduPanel.getStep();
		while (etapeActuelle < 9) {
			String input = scanner.nextLine();
			for (String string : wordArray) {
				if (string.equals(input)) {
					correctGuess = true;
					break;
				}
			}
			if (correctGuess)
				continue;
			etapeActuelle = penduPanel.getStep();
			if (etapeActuelle < 11) {
				penduPanel.setEtape(etapeActuelle + 1); // Augmente l'étape du pendu
			} else {
				break;
			}
		}
		System.out.println("Le jeu est terminé !");
		scanner.close();
	}
}
