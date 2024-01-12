package test;
import javax.swing.*;

import arbre.DicTree;
import arbre.Node;
import interfaceGraphique.HangManGraphic;
import java.util.Scanner;

public class TestJeu {

	public static void main(String[] args) {
		
		
		DicTree arbre=new DicTree("$");
		arbre.addWord("cas");
		arbre.addWord("ce");
		arbre.addWord("ces");
		arbre.addWord("ci");
		arbre.addWord("de");
		arbre.addWord("des");
		arbre.addWord("do");
		//arbre.insererChaine("cici");
		arbre.drawTree();
		//Début du jeu
		
		//Construire l'interface graphique
		/*JFrame frame = new JFrame("Jeu du Pendu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HangManGraphic penduPanel = new HangManGraphic();
        frame.add(penduPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/

        //L'utilisateur va commencer à jouer
       /* System.out.println("Donner moi une lettre : ");
        Scanner scanner = new Scanner(System.in);
        int etapeActuelle = penduPanel.getStep();
        while (etapeActuelle<9) {
            String input = scanner.nextLine(); 
            etapeActuelle = penduPanel.getStep();
            if (etapeActuelle < 11) {
                penduPanel.setEtape(etapeActuelle + 1); // Augmente l'étape du pendu
            } else {
                
                break;
            }
        }
        System.out.println("Le jeu est terminé !");*/
	}

}
