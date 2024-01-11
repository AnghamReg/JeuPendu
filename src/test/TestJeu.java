package test;

import javax.swing.*;

import interfaceGraphique.PenduGraphique;
import model.Dict;

import java.util.Scanner;

public class TestJeu {

    public static void main(String[] args) throws Exception {
    	
    	 Dict d = new Dict();
         d.fillDict();
         //Dict test = d.createDict();
         d.traversePreOrder();
    	d.dessinerArbre();
    	
        JFrame frame = new JFrame("Jeu du Pendu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PenduGraphique penduPanel = new PenduGraphique();
        frame.add(penduPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Donner moi une lettre : ");
        Scanner scanner = new Scanner(System.in);
        int etapeActuelle = penduPanel.getEtape();
        while (etapeActuelle < 9) {
            String input = scanner.nextLine();
            etapeActuelle = penduPanel.getEtape();
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
