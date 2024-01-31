package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import interfaceGraphique.MenuWindow.Level;

public class MenuInterface extends JPanel {
	private Clip backgroundMusic;
	private String playerName;
	private GameWindow gameInterface;
	private BufferedImage image;
	private JFrame menuWindow;
	private Clip backgroundMusicMenu;

	
	public MenuInterface(JFrame mw) {
		menuWindow=new JFrame();
		this.menuWindow=mw;
		setPreferredSize(new Dimension(500, 600));
		image = new BufferedImage(500, 600, BufferedImage.TYPE_INT_ARGB);
		 menuInterface();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
	   super.paintComponent(g);
	}
	
	
	private JLabel getLabel(String text) {
		JLabel l = new JLabel(text);
		return l;
	}
	
	private JLabel getImage(String url, int width) throws IOException {
		URL gifUrl = new URL(url);
		BufferedImage originalImage = ImageIO.read(gifUrl);

		// Resize the image while maintaining the aspect ratio
		int targetWidth = width; // Set your desired width
		int targetHeight = (int) (originalImage.getHeight() * ((double) targetWidth / originalImage.getWidth()));

		// Create a new ImageIcon with the resized image
		ImageIcon resizedIcon = new ImageIcon(
				originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH));

		JLabel gifLabel = new JLabel(resizedIcon);
		return gifLabel;
	}
	
	private JTextField getTextField(int width, int height) {
		JTextField txtField = new JTextField();
		txtField.setPreferredSize(new Dimension(width, height));
		return txtField;
	}
	
	private JButton getButton(String text, String nameFont, int sizeFont) {
		JButton playButton = new JButton(text);
		// Font
		Font customFont = new Font(nameFont, Font.ITALIC, sizeFont);
		playButton.setFont(customFont);
		playButton.setBorder(BorderFactory.createEmptyBorder());
		return playButton;
	}

	public void menuInterface() {
		 //this.playBackgroundMusic();

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		int line = 0;

		JLabel l1 = getLabel("Welcome to Hangman Game !");
		l1.setFont(new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, 20));
		// ligne 0
		gbc.gridy = line;
		gbc.insets = new Insets(0, 0, 20, 0);
		add(l1, gbc);

		String url = "https://replit.com/cdn-cgi/image/quality=80,metadata=copyright,format=auto/https://storage.googleapis.com/replit/images/1585940175021_39360a4db2b546a4455230a428a321de.png";
		try {
			JLabel gifLabel = getImage(url, 400);
			// ligne 1
			line++;
			gbc.gridy = line;
			gbc.insets = new Insets(0, 0, 80, 0);
			add(gifLabel, gbc);

		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel l2 = getLabel("Enter your name :");
		// ligne 2
		line++;
		gbc.gridy = line;
		gbc.insets = new Insets(0, 0, 8, 0);
		add(l2, gbc);

		JTextField nameTxt = getTextField(100, 30);
		// ligne 3
		line++;
		gbc.gridy = line;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(nameTxt, gbc);

		JButton playButton = getButton("   PLAY   ", Font.SANS_SERIF, 35);
		playButton.setBackground(Color.pink);

		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (nameTxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(MenuInterface.this, "Please enter your name before playing.",
							"Error", JOptionPane.ERROR_MESSAGE);

				} else {
					playerName = nameTxt.getText();
					// Appeler la méthode pour afficher l'interface de choix de difficulté
					showDifficultySelection();
				}
			}
		});
		// ligne 4
		line++;
		gbc.gridy = line;
		add(playButton, gbc);

	}
	private void removeWindowAndStartGame(Level diff,JFrame difficultyFrame,JFrame menuWindow) {
	difficultyFrame.dispose();
	menuWindow.dispose();
	this.backgroundMusic.stop();
	gameInterface = new GameWindow(diff);
	}
	
	private void showDifficultySelection() {
		JFrame difficultyFrame = new JFrame("Choose Difficulty");
		difficultyFrame.setSize(300, 200);
		difficultyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel difficultyLabel = new JLabel("Select Difficulty:");
		JRadioButton easyRadioButton = new JRadioButton("Easy");
		JRadioButton mediumRadioButton = new JRadioButton("Medium");
		JRadioButton hardRadioButton = new JRadioButton("Hard");

		ButtonGroup difficultyGroup = new ButtonGroup();
		difficultyGroup.add(easyRadioButton);
		difficultyGroup.add(mediumRadioButton);
		difficultyGroup.add(hardRadioButton);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (easyRadioButton.isSelected()) {
					removeWindowAndStartGame(Level.LOW,difficultyFrame,menuWindow);
		
				} else if (mediumRadioButton.isSelected()) {
					removeWindowAndStartGame(Level.LOW,difficultyFrame,menuWindow);
					
				} else if (hardRadioButton.isSelected()) {
					removeWindowAndStartGame(Level.LOW,difficultyFrame,menuWindow);
					
				} else {
					JOptionPane.showMessageDialog(difficultyFrame, "Please select a difficulty.");
				}
			}
		});

		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridLayout(4, 1));
		difficultyPanel.add(difficultyLabel);
		difficultyPanel.add(easyRadioButton);
		difficultyPanel.add(mediumRadioButton);
		difficultyPanel.add(hardRadioButton);

		difficultyFrame.setLayout(new BorderLayout());
		difficultyFrame.add(difficultyPanel, BorderLayout.CENTER);
		difficultyFrame.add(startButton, BorderLayout.SOUTH);
		difficultyFrame.setLocationRelativeTo(null);
		difficultyFrame.setVisible(true);
	}
}
