package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PenduGraphique extends JPanel {
	 private BufferedImage penduPartiel;
	 private int etape = 0;

    public int getEtape() {
    	return etape;
    }

    public PenduGraphique() {
        setPreferredSize(new Dimension(300, 400));
        penduPartiel = new BufferedImage(300, 400, BufferedImage.TYPE_INT_ARGB);
        initialiserPendu();
    }

    private void initialiserPendu() {
        Graphics2D g2d = penduPartiel.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3)); // Épaisseur du trait
        g2d.drawRect(15, 15, 270, 370);
        g2d.dispose();
    }

    public void setEtape(int nouvelleEtape) {
        etape = nouvelleEtape;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(penduPartiel, 0, 0, this); // Dessine le pendu partiel sur le JPanel
        Graphics2D g2d = (Graphics2D) g;
        dessinerPendu(g2d, etape); // Dessine l'étape actuelle du pendu
    }

    private void dessinerPendu(Graphics g, int etape) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED); // Changez la couleur ou les paramètres selon vos besoins

        switch (etape) {
        case 1 : 
            g2d.setColor(Color.BLACK);
            g2d.drawLine(50, 350, 50, 50); // Poteau vertical initial
            break;
            case 2:
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
                g2d.drawLine(50, 50, 150, 50); // Barre horizontale
                break;
            case 3:
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100); // Corde
                break;
            case 4:
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                	g2d.drawOval(125, 100, 50, 50); // Dessiner la tête
            	    break;
            case 5 : 
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);// Dessiner le cou
            	break;
            case 6:
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);
            	g2d.setColor(Color.BLUE);
             	g2d.drawRect(135, 175, 30, 75); // Dessiner le corps
             	break ;
            case 7 : 
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);
            	g2d.setColor(Color.BLUE);
             	g2d.drawRect(135, 175, 30, 75);
             	g2d.setColor(Color.GRAY);
            	g2d.drawLine(120, 200, 135, 225); // Dessiner la main gauche
                break;
            case 8 :
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);
            	g2d.setColor(Color.BLUE);
             	g2d.drawRect(135, 175, 30, 75);
             	g2d.setColor(Color.GRAY);
            	g2d.drawLine(120, 200, 135, 225); 
                g2d.drawLine(180, 200, 165, 225); //Dessiner la main droite
                break;
            case 9 :
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);
            	g2d.setColor(Color.BLUE);
             	g2d.drawRect(135, 175, 30, 75);
             	g2d.setColor(Color.GRAY);
            	g2d.drawLine(120, 200, 135, 225); 
                g2d.drawLine(180, 200, 165, 225);
            	g2d.drawLine(135, 250, 125, 300); // Dessiner la jambe gauche
                break;
            case 10 :
            	g2d.setColor(Color.BLACK);
                g2d.drawLine(50, 350, 50, 50);
            	g2d.drawLine(50, 50, 150, 50);
            	g2d.setColor(Color.ORANGE);
                g2d.drawLine(150, 50, 150, 100);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(125, 100, 50, 50);
            	g2d.drawLine(150, 150, 150, 175);
            	g2d.setColor(Color.BLUE);
             	g2d.drawRect(135, 175, 30, 75);
             	g2d.setColor(Color.GRAY);
            	g2d.drawLine(120, 200, 135, 225); 
                g2d.drawLine(180, 200, 165, 225);
            	g2d.drawLine(135, 250, 125, 300); 
            	g2d.drawLine(165, 250, 175, 300); // Dessiner la jambe droite
                g2d.setColor(Color.RED);
                g2d.drawString("Vous avez perdu !", 100, 320); // Afficher "Vous avez perdu !"
                break;
                

        }
    }

}
