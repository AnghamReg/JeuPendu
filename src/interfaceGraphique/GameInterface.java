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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaceGraphique.MenuWindow.Level;

public class GameInterface extends JPanel {
	private JFrame gameWindow;
	private HangmanDrawingPanel hangmanPanel;
	private ArrayList<Integer> indices;
	private Level difficulty;
	
	public GameInterface(JFrame gw, Level diff) {
		indices=new ArrayList<>();
		this.gameWindow=new JFrame();
		this.gameWindow=gw;
		setPreferredSize(new Dimension(600, 700));
		this.startGame(diff);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        startGame(this.difficulty);
        this.hangmanPanel.repaint();
        }

	
	public  void updateIndices(String letter, String word) {
        char targetLetter = letter.charAt(0);

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == targetLetter) {
                indices.add(i);
            }
        }
    }
	
	public void startGame(Level difficulty) {
		this.difficulty=difficulty;
		setLayout(new BorderLayout());
        this.hangmanPanel=new HangmanDrawingPanel();
       add(this.hangmanPanel,BorderLayout.NORTH);
        
		
		JPanel user=new JPanel();
		user.setPreferredSize(new Dimension(500, 200));
		user.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();	
		 gbc.gridx = 0;
         gbc.gridy = 0;
         
         JLabel l=new JLabel("Enter a letter :"); 
         user.add(l,gbc);
         System.out.println("Label gridy = "+gbc.gridy);
         
         JTextField le=new JTextField();
         le.setPreferredSize(new Dimension(30, 30));
         gbc.gridy=1;
         user.add(le,gbc);
		
         JButton okButton=new JButton("  OK  ");
 		Font customFont = new Font(Font.SANS_SERIF, Font.ITALIC, 10);
         okButton.setFont(customFont);
         okButton.setBorder(BorderFactory.createEmptyBorder());
         gbc.gridy=2;
         user.add(okButton,gbc);
         
         add(user,BorderLayout.CENTER);
         

         
         gbc.gridy=1;
         int line=1;
         int column=1;
       //Add first letter
    
         String word="happy";
         String[] wordCrypted=word.toLowerCase().split("");
         if(!indices.contains(0)) {
        	 wordCrypted[0]="_";  
         }else {
        	 wordCrypted[0]=word.toLowerCase().split("")[0];
         }
   
         gbc.insets=new Insets(0,60,0,0);
       	gbc.gridx=column;
       	gbc.gridy=line;
       	JLabel wordLabel=new JLabel("Word to guess :");
       	user.add(wordLabel,gbc);
       	column++;
       	gbc.gridx=column;
       	JLabel letterToGuess=new JLabel(wordCrypted[0]+" ");
       	gbc.insets=new Insets(0,10,0,0);
       	user.add(letterToGuess,gbc);
       	column++;
         
       	gbc.insets=new Insets(0,0,0,0);
         for(int j=1;j<"happy".length();j++) {
        	 if(!indices.contains(j)) {
            	 wordCrypted[j]="_";  
             }else {
            	 wordCrypted[j]=word.toLowerCase().split("")[j];
             }
         	gbc.gridx=column;
         	gbc.gridy=line;
         	JLabel letterToGuessNext=new JLabel(wordCrypted[j]+" ");
         	user.add(letterToGuessNext,gbc);
         	column++;
         }
         System.out.println("wordCrypted is : "+ String.join(" ", wordCrypted));
         
         okButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
           	  JTextField letterField = le;
                 String enteredLetter = letterField.getText(); 
             	if(enteredLetter.isEmpty()) {
                     JOptionPane.showMessageDialog(GameInterface.this, "Please enter a letter.", "Error", JOptionPane.ERROR_MESSAGE);
             	}else {
             		if(enteredLetter.length()>1) {
                     JOptionPane.showMessageDialog(GameInterface.this, "Please enter a single letter.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
             		}else {
             			if(hangmanPanel.getStep()<10) {
                        if(word.contains(enteredLetter)) {
                        	le.setText("");
                        	System.out.println("happy contains : "+enteredLetter.toString()+" in index : "+word.indexOf(enteredLetter.toString()));
                        	updateIndices(enteredLetter,word);
                        	System.out.println(indices.toString());
                        	revalidate();
                        	startGame(difficulty);
                        	if(indices.size()==word.length()) {
                        		new WinningFrame(word,gameWindow);
                        	}
                     	
                        }else {
                        	le.setText("");
                        	 revalidate();
                        	hangmanPanel.addOneStep();
                        	le.requestFocus();
                        	if(hangmanPanel.getStep()>=10) {
                        		new RetryFrame(word, gameWindow);
                        		
                        	}
                        	
                        }
             			
             			}else {
             				//lose
             				
             			}
             		}

             	}
             }
         });
        
	}

}
