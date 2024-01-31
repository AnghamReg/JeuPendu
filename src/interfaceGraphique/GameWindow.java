package interfaceGraphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaceGraphique.MenuWindow.Level;

public class GameWindow extends JFrame {	
	private GameInterface gameInterface;
	
	public GameWindow(Level diff) {
		this.setTitle("Hangman Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameInterface=new GameInterface(this,diff);
		this.add(gameInterface);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	

}
