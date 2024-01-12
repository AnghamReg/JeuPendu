package test;
import javax.swing.*;

import arbre.Arbre;
import arbre.Noeud;
import interfaceGraphique.PenduGraphique;
import java.util.Scanner;

public class TestJeu {

	public static void main(String[] args) {
		
		
		Arbre arbre=new Arbre("$");
		arbre.insererChaine("cas");
		arbre.insererChaine("ce");
		arbre.insererChaine("ces");
		arbre.insererChaine("ci");
		arbre.insererChaine("de");
		arbre.insererChaine("des");
		arbre.insererChaine("do");
		//arbre.insererChaine("cici");
		arbre.dessinerArbre();
		//Début du jeu
		
		//Construire l'interface graphique
		/*JFrame frame = new JFrame("Jeu du Pendu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PenduGraphique penduPanel = new PenduGraphique();
        frame.add(penduPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/

        //L'utilisateur va commencer à jouer
        /*System.out.println("Donner moi une lettre : ");
        Scanner scanner = new Scanner(System.in);
        int etapeActuelle = penduPanel.getEtape();
        while (etapeActuelle<9) {
            String input = scanner.nextLine(); 
            etapeActuelle = penduPanel.getEtape();
            if (etapeActuelle < 11) {
                penduPanel.setEtape(etapeActuelle + 1); // Augmente l'étape du pendu
            } else {
                
                break;
            }
        }
        System.out.println("Le jeu est terminé !");*/
	}

}
