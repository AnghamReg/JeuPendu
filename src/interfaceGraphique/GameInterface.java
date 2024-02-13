package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arbre.Classification;
import interfaceGraphique.MenuWindow.Level;

public class GameInterface extends JPanel {
	private JFrame gameWindow;
	private HangmanDrawingPanel hangmanPanel;
	private ArrayList<Integer> indexRevealed;
	private Level difficulty;
	private String playerName;
	private String word = "congratulations";

	public GameInterface(JFrame gw, Level diff, String playerName) {
		this.playerName = playerName;
		indexRevealed = new ArrayList<Integer>();
		this.gameWindow = new JFrame();
		this.gameWindow = gw;
		setPreferredSize(new Dimension(600, 500)); // 600,600 works
		this.difficulty = diff;
		Classification classification = new Classification();
		switch (difficulty.toString().toLowerCase()) {
			case "low":
				if (this.indexRevealed.isEmpty()) {
					this.word = classification.randomWord(1);
					chooseRandomIndexToDisplay();
				}
				break;
			case "medium":
				this.word = classification.randomWord(2);

				break;
			default:

		}
		System.out.println(diff);
		// this.startGame(diff);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// startGame(this.difficulty);
		startGame();
		this.hangmanPanel.repaint();
	}

	public void updateIndices(String letter) {
		System.out.println("in updateIndices");
		char targetLetter = letter.charAt(0);

		for (int i = 0; i < this.word.length(); i++) {
			if (word.toLowerCase().charAt(i) == targetLetter) {
				System.out.println("target letter : "+targetLetter+" = "+word.charAt(i));
				if (!indexRevealed.contains(i)) {
					indexRevealed.add(i);
				}
			}
		}
	}

	private void chooseRandomIndexToDisplay() {
		int n = this.word.length();
		Random random = new Random();
		for (int i = 0; i < n / 2; i++) {
			this.indexRevealed.add(random.nextInt(n) + 1);
		}
	}

	// public void startGame(Level difficulty) {
	public void startGame() {
		System.err.println("starting");
		// this.difficulty = difficulty;
		setLayout(new BorderLayout());
		this.hangmanPanel = new HangmanDrawingPanel();
		add(this.hangmanPanel, BorderLayout.NORTH);

		JPanel user = new JPanel();
		user.setPreferredSize(new Dimension(500, 200));
		user.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel l = new JLabel("Enter a letter :");
		user.add(l, gbc);
		System.out.println("Label gridy = " + gbc.gridy);

		// le = letter guessed
		JTextField le = new JTextField();
		le.setPreferredSize(new Dimension(30, 30));
		gbc.gridy = 1;
		user.add(le, gbc);

		JButton okButton = new JButton("  OK  ");
		Font customFont = new Font(Font.SANS_SERIF, Font.ITALIC, 10);
		okButton.setFont(customFont);
		okButton.setBorder(BorderFactory.createEmptyBorder());
		gbc.gridy = 2;
		user.add(okButton, gbc);

		add(user, BorderLayout.CENTER);

		gbc.gridy = 1;
		int line = 1;
		int column = 1;

		// the word to guess
		String[] wordCrypted = word.toLowerCase().split("");
		if (!indexRevealed.contains(0)) {
			wordCrypted[0] = "_";
		} else {
			wordCrypted[0] = word.toLowerCase().split("")[0];
		}

		gbc.insets = new Insets(0, 60, 0, 0);
		gbc.gridx = column;
		gbc.gridy = line;
		JLabel wordLabel = new JLabel("Word to guess :");
		user.add(wordLabel, gbc);
		column++;
		gbc.gridx = column;
		JLabel letterToGuess = new JLabel(wordCrypted[0] + " ");
		gbc.insets = new Insets(0, 10, 0, 0);
		user.add(letterToGuess, gbc);
		column++;

		gbc.insets = new Insets(0, 0, 0, 0);
		for (int j = 1; j < word.length(); j++) {
			if (!indexRevealed.contains(j)) {
				wordCrypted[j] = "_";
			} else {
				wordCrypted[j] = word.toLowerCase().split("")[j];
			}
			gbc.gridx = column;
			gbc.gridy = line;
			JLabel letterToGuessNext = new JLabel(wordCrypted[j] + " ");
			user.add(letterToGuessNext, gbc);
			column++;
		}
		gbc.gridx = column;
		gbc.insets = new Insets(0, 5, 0, 0);
		JLabel wordLength = new JLabel("(" + word.length() + ")");
		user.add(wordLength, gbc);
		gbc.insets = new Insets(0, 0, 0, 0);
		System.out.println("wordCrypted is : " + String.join(" ", wordCrypted));

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("the word is : " + word);
				JTextField letterField = le;
				String enteredLetter = letterField.getText().toLowerCase();
				if (enteredLetter.isEmpty()) {
					JOptionPane.showMessageDialog(GameInterface.this, "Please enter a letter.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (enteredLetter.length() > 1) {
						JOptionPane.showMessageDialog(GameInterface.this, "Please enter a single letter.",
								"Invalid Input", JOptionPane.ERROR_MESSAGE);
					} else {
						if (hangmanPanel.getStep() < 10) {
							if (word.toLowerCase().contains(enteredLetter)) {
								System.out.println("word : "+word+" contains : "+enteredLetter);
								le.setText("");
								updateIndices(enteredLetter);
								System.out.println("index are : "+indexRevealed.toString());
								revalidate();
								le.requestFocus();
								startGame();
								if (indexRevealed.size() == word.length()) {
									System.out.println("revealed indexes are : " + indexRevealed.toString()
											+ " word leng= " + word.length());
									new WinningFrame(word, gameWindow, playerName);
								}

							} else {
								le.setText("");
								revalidate();
								hangmanPanel.addOneStep();
								le.requestFocus();
								if (hangmanPanel.getStep() >= 10) {
									new RetryFrame(word, gameWindow, playerName);

								}

							}

						}
					}

				}
			}
		});

	}

}
