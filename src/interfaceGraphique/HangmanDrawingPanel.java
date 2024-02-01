package interfaceGraphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class HangmanDrawingPanel extends JPanel{
	 static int step=0;
	
	public HangmanDrawingPanel() {
		this.setPreferredSize(new Dimension(600, 400));
	}
	
	public HangmanDrawingPanel(int step) {
        this.step = step;
    }
	
	public int getStep() {
		return step;
	}

	public void addOneStep() {
		this.step=this.step+1;
		repaint();
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        drawHangMan(g);
	    }
	 
	
	private void drawHangMan(Graphics g) {
		int panelWidth = getWidth();  // Get the width of the panel
	    int panelHeight = getHeight();  // Get the height of the panel

	    int drawingWidth = 270;  // Width of the hangman drawing
	    int drawingHeight = 370; // Height of the hangman drawing

	    int startX = (panelWidth - drawingWidth) / 2;  // Calculate the starting X coordinate
	    int startY = (panelHeight - drawingHeight) / 2;  // Calculate the starting Y coordinate

	    g.setColor(Color.BLACK);
	    ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
	    g.drawRect(startX, startY, drawingWidth, drawingHeight);
		
		
	    switch (step) {
        case 1:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20); // Poteau vertical initial
            break;
        case 2:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20); // Barre horizontale
            break;
        case 3:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70); // Corde
            break;
        case 4:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50); // Dessiner la tête
            break;
        case 5:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145); // Dessiner le cou
            break;
        case 6:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145);
            g.setColor(Color.BLUE);
            g.drawRect(startX + 120, startY + 145, 30, 75); // Dessiner le corps
            break;
        case 7:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145);
            g.setColor(Color.BLUE);
            g.drawRect(startX + 120, startY + 145, 30, 75);
            g.setColor(Color.GRAY);
            g.drawLine(startX + 100, startY + 170, startX + 120, startY + 195); // Dessiner la main gauche
            break;
        case 8:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145);
            g.setColor(Color.BLUE);
            g.drawRect(startX + 120, startY + 145, 30, 75);
            g.setColor(Color.GRAY);
            g.drawLine(startX + 100, startY + 170, startX + 120, startY + 195);
            g.drawLine(startX + 165, startY + 170, startX + 150, startY + 195); // Dessiner la main droite
            break;
        case 9:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145);
            g.setColor(Color.BLUE);
            g.drawRect(startX + 120, startY + 145, 30, 75);
            g.setColor(Color.GRAY);
            g.drawLine(startX + 100, startY + 170, startX + 120, startY + 195);
            g.drawLine(startX + 165, startY + 170, startX + 150, startY + 195);
            g.drawLine(startX + 125, startY + 220, startX + 115, startY + 270); // Dessiner la jambe gauche
            break;
        case 10:
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Épaisseur du trait
            g.drawRect(startX, startY, drawingWidth, drawingHeight);
            g.setColor(Color.BLACK);
            g.drawLine(startX + 35, startY + 320, startX + 35, startY + 20);
            g.drawLine(startX + 35, startY + 20, startX + 135, startY + 20);
            g.setColor(Color.ORANGE);
            g.drawLine(startX + 135, startY + 20, startX + 135, startY + 70);
            g.setColor(Color.GRAY);
            g.drawOval(startX + 110, startY + 70, 50, 50);
            g.drawLine(startX + 135, startY + 120, startX + 135, startY + 145);
            g.setColor(Color.BLUE);
            g.drawRect(startX + 120, startY + 145, 30, 75);
            g.setColor(Color.GRAY);
            g.drawLine(startX + 100, startY + 170, startX + 120, startY + 195);
            g.drawLine(startX + 165, startY + 170, startX + 150, startY + 195);
            g.drawLine(startX + 125, startY + 220, startX + 115, startY + 270);
            g.drawLine(startX + 142, startY + 220, startX + 152, startY + 270); // Dessiner la jambe droite
            g.setColor(Color.RED);
            Font boldFont = new Font("Arial", Font.BOLD, 20);
            g.setFont(boldFont);
            g.drawString("You lost !", startX + 110, startY + 330); // Afficher "Vous avez perdu !"
            break;
    }
		
    }


}
