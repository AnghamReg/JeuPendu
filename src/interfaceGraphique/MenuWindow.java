package interfaceGraphique;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class MenuWindow extends JFrame {
	enum Level {
		LOW, MEDIUM, HIGH
	}
	private Clip backgroundMusic;
	private MenuInterface menuInterface;
	

	public MenuWindow() {
		this.setTitle("Hangman Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuInterface=new MenuInterface(this);
		this.add(menuInterface);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		playBackgroundMusic();
	}
	
	private void playBackgroundMusic() {
	    try {
	        URL soundUrl = MenuWindow.class.getResource("/resources/backgroundMusic.wav"); // Adjust the path
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
	        backgroundMusic = AudioSystem.getClip();
	        backgroundMusic.open(audioInputStream);
	        backgroundMusic.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
