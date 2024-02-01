package interfaceGraphique;


import javax.swing.JFrame;

import interfaceGraphique.MenuWindow.Level;

public class GameWindow extends JFrame {	
	private GameInterface gameInterface;
	
	public GameWindow(Level diff, String playerName) {
		String mode;
		System.out.println("the mode is : "+diff.toString().toLowerCase());
		switch(diff.toString().toLowerCase()) {
		case "low":
			mode="Easy";
			break;
		case "medium":
			mode="Medium";
			break;
		default :
			mode="Hard";
		}
		this.setTitle("Hangman Game [ "+mode+" Mode ]");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameInterface=new GameInterface(this,diff,playerName);
		this.add(gameInterface);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	

}
