package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInputWordPanel extends JPanel{
	private String word;
	private boolean hasWin=false,hasLost=false;
	private JButton okButton,retryButton;
	private JTextField letter;
	private String[] wordCrypted;
	
	
	public UserInputWordPanel(String w){
		this.setPreferredSize(new Dimension(500, 300));
		//setPreferredSize(this.getPreferredSize());
		this.okButton=new JButton("  OK  ");
		Font customFont = new Font(Font.SANS_SERIF, Font.ITALIC, 10);
        this.okButton.setFont(customFont);
        this.okButton.setBorder(BorderFactory.createEmptyBorder());
		System.out.println("const userinput \n");
		this.word=w;
	}
	
	public JButton getOkButton() {
		return okButton;
	}
	

	public void hasWin() {
		hasWin=true;
	}
	
	public void hasLost() {
		hasLost=true;
	}
	
	public String getWord() {
		return word;
	}
	
	public JTextField getLetter() {
		return letter;
	}
	
	
	 private JTextField getTextField(int width,int height) {
	    	JTextField txtField = new JTextField();
	    	txtField.setPreferredSize(new Dimension(width, height)); 
	    	return txtField;
	    }
	 
	 private JButton getButton(String text,String nameFont,int sizeFont ) {
	    	JButton playButton = new JButton(text);
	    	//Font
	    	Font customFont = new Font(nameFont, Font.ITALIC, sizeFont);
	    	playButton.setFont(customFont);
	    	playButton.setBorder(BorderFactory.createEmptyBorder());
	    	return playButton;
	    }
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paint");
        writingArea();
        }
        
      private void writingArea() {
    	  this.setLayout(new GridBagLayout());
    	  GridBagConstraints gbc = new GridBagConstraints();	
          gbc.gridx = 0;
          gbc.gridy = 0;
          
          JLabel l=new JLabel("Enter a letter :"); 
          add(l,gbc);
          System.out.println("Label gridy = "+gbc.gridy);
          
          JTextField le=new JTextField();
          le.setPreferredSize(new Dimension(30, 30));
          
//          this.letter = new JTextField();
//          this.letter.setPreferredSize(new Dimension(30, 30)); 
          gbc.gridy=1;
          add(le,gbc);
         

          gbc.gridy=2;
          add(okButton,gbc);
          
          //////////////////
          
          okButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  System.out.println("you have clicked the button");
            	  JTextField letterField = le;
                  String enteredLetter = letterField.getText(); 
              	if(enteredLetter.isEmpty()) {
              		System.out.println("your empty letter is : "+le.getText()+"\n");
              		System.out.println("empty letter");
                      JOptionPane.showMessageDialog(UserInputWordPanel.this, "Please enter a letter.", "Error", JOptionPane.ERROR_MESSAGE);
              	}else {
              		System.out.println("your  letter is : "+le.getText()+"\n");
              		System.out.println("not empty letter");
              		if(enteredLetter.length()>1) {
                      JOptionPane.showMessageDialog(UserInputWordPanel.this, "Please enter a single letter.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
              		}else {

              		}

              	}
              }
          });
          
          ////////////////

          gbc.gridy=1;
          int line=1;
          int column=1;
          //Add first letter
          wordCrypted=word.split("");
          wordCrypted[0]="_"; 
          gbc.insets=new Insets(0,60,0,0);
        	gbc.gridx=column;
        	gbc.gridy=line;
        	JLabel wordLabel=new JLabel("Word to guess :");
        	add(wordLabel,gbc);
        	column++;
        	gbc.gridx=column;
        	JLabel letterToGuess=new JLabel(wordCrypted[0]+" ");
        	gbc.insets=new Insets(0,10,0,0);
        	add(letterToGuess,gbc);
        	column++;
          
        	gbc.insets=new Insets(0,0,0,0);
          for(int j=1;j<word.length();j++) {
          	wordCrypted[j]="_"; 
          	gbc.gridx=column;
          	gbc.gridy=line;
          	JLabel letterToGuessNext=new JLabel(wordCrypted[j]+" ");
          	add(letterToGuessNext,gbc);
          	column++;
          }
         line ++;
          column=0;
         if(hasWin || hasLost) {
          	String finalMessage="";
          	 if(hasWin) {
               	 finalMessage="Congrats ! you have won !";
               }else if(hasLost) {
               	 finalMessage="Oopsi ! You can try again !";
               }
          	 JLabel finalMessageLabel=new JLabel(finalMessage);
            	add(finalMessageLabel,gbc);
            	JButton retryButton=new JButton();
  	        Font customFont2 = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
  	    	retryButton.setFont(customFont2);
  	    	retryButton.setBorder(BorderFactory.createEmptyBorder());
  	    	retryButton.setBackground(Color.pink);
  	    	line ++;
  	    	add(retryButton,gbc);
  	    	
  	    	
  	    	
      }             
        
  
        
        
    }

}
