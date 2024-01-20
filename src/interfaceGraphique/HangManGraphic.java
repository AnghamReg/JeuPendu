package interfaceGraphique;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManGraphic extends JPanel {
	 private BufferedImage image;
	 private int step = 0;
	 private String playerName;
	 private Clip backgroundMusic;

    public int getStep() {
    	return step;
    }

    public HangManGraphic() {
        setPreferredSize(new Dimension(500, 600));
        image = new BufferedImage(500, 600, BufferedImage.TYPE_INT_ARGB);
        menuInterface();
    }
    
    private void frameHangMan() {
    	Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3)); // Épaisseur du trait
        g2d.drawRect(15, 15, 270, 370);
        g2d.dispose();
    }
    
    private GridBagConstraints getGridBad() {
    	setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        return gbc;
    }
    
    private JButton getButton(String text,String nameFont,int sizeFont ) {
    	JButton playButton = new JButton(text);
    	//Font
    	Font customFont = new Font(nameFont, Font.ITALIC, sizeFont);
    	playButton.setFont(customFont);
    	playButton.setBorder(BorderFactory.createEmptyBorder());
    	return playButton;
    }
    
    private JLabel getImage(String url,int width) throws IOException{
    	URL gifUrl = new URL(url);
        BufferedImage originalImage = ImageIO.read(gifUrl);

        // Resize the image while maintaining the aspect ratio
        int targetWidth = width; // Set your desired width
        int targetHeight = (int) (originalImage.getHeight() * ((double) targetWidth / originalImage.getWidth()));

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon = new ImageIcon(originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH));

        JLabel gifLabel = new JLabel(resizedIcon);
        return gifLabel;
    }
    
    private JLabel getLabel(String text) {
    	JLabel l=new JLabel(text); 
    	return l;
    }
    
    private JTextField getTextField(int width,int height) {
    	JTextField txtField = new JTextField();
    	txtField.setPreferredSize(new Dimension(width, height)); 
    	return txtField;
    }

    private void menuInterface() {
    	//this.playBackgroundMusic();
    	
        GridBagConstraints gbc = getGridBad();
        gbc.gridx = 0;
        gbc.gridy = 0;
        int line=0;
        
       JLabel l1= getLabel("Welcome to Hangman Game !"); 
       l1.setFont(new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, 20));
       //ligne 0
       gbc.gridy=line;
       gbc.insets = new Insets(0, 0, 20, 0); 
       add(l1,gbc);
      
        
        String url="https://replit.com/cdn-cgi/image/quality=80,metadata=copyright,format=auto/https://storage.googleapis.com/replit/images/1585940175021_39360a4db2b546a4455230a428a321de.png";
        try {
        	JLabel gifLabel=getImage(url,400);
        	//ligne 1
        	line++;
        	gbc.gridy=line;
        	gbc.insets = new Insets(0, 0, 80, 0); 
            add(gifLabel, gbc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JLabel l2= getLabel("Enter your name :"); 
      //ligne 2
        line++;
        gbc.gridy=line;
        gbc.insets = new Insets(0, 0, 8, 0); 
        add(l2,gbc);
        
        JTextField nameTxt = getTextField(100,30);
      //ligne 3
        line++;
        gbc.gridy=line;
        gbc.insets = new Insets(0, 0, 10, 0); 
        add(nameTxt,gbc);
             
        JButton playButton=getButton("   PLAY   ",Font.SANS_SERIF,35);
    	playButton.setBackground(Color.pink);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(nameTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(HangManGraphic.this, "Please enter your name before playing.", "Error", JOptionPane.ERROR_MESSAGE);

            	}else {
            		playerName=nameTxt.getText();
                // Appeler la méthode pour afficher l'interface de choix de difficulté
                showDifficultySelection();
            	}
            }
        });
        //ligne 4
        line++;
        gbc.gridy = line;
        add(playButton, gbc);

    }
    
    private void playBackgroundMusic() {
        try {
            URL soundUrl = HangManGraphic.class.getResource("/resources/backgroundMusic.wav"); // Adjust the path
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                 // Add logic to start the game with the selected difficulty
                 // You can access the selected difficulty using the isSelected() method
                 if (easyRadioButton.isSelected()) {
                	 difficultyFrame.setVisible(false);
                     startGame("easy");
                 } else if (mediumRadioButton.isSelected()) {
                	 difficultyFrame.setVisible(false);
                     startGame("medium");
                 } else if (hardRadioButton.isSelected()) {
                	 difficultyFrame.setVisible(false);
                     startGame("hard");
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
    
    private void startGame(String difficulty) {
    	removeAll();
    	this.frameHangMan();
        revalidate();
        repaint();
        GridBagConstraints gbc= this.getGridBad();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        int line=0;
        int column=0;
        
        JLabel label=this.getLabel("Enter a letter :");
       // gbc.insets=new Insets(0,200,0,0);
        add(label,gbc);
        
        JTextField letter=this.getTextField(30, 30);
        line++;
        gbc.gridy=line;
       // gbc.insets=new Insets(0,200,0,0);
        add(letter,gbc);
        
        JButton okButton=this.getButton("  Ok  ",Font.SANS_SERIF,10);
        line++;
        gbc.gridy=line;
       // gbc.insets=new Insets(0,200,0,0);
        add(okButton,gbc);
        
        //gbc.insets=new Insets(0,0,0,5);
        line++;
        String word="happy";
        String[] wordCrypted=word.split("");
        for(int j=0;j<word.length();j++) {
        	wordCrypted[j]="_"; 
        	gbc.gridx=column;
        	gbc.gridy=line;
        	JLabel letterToGuess=this.getLabel(wordCrypted[j]);
        	add(letterToGuess,gbc);
        	column++;
        }
        //gbc.insets=new Insets(0,0,0,0);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(letter.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(HangManGraphic.this, "Please enter a letter.", "Error", JOptionPane.ERROR_MESSAGE);
            	}else {
            		if(letter.getText().length()>1) {
                    JOptionPane.showMessageDialog(HangManGraphic.this, "Please enter a single letter.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            		}else {
            			if(step<10) {
            				step = step+1;
                			System.out.println("step : "+step);
                			revalidate();
                	        repaint();
                	        if(step==10) {
                	        	revalidate();
                    	        repaint();
                    	        // You word was : . It's okay playerName you can retry !
                				JLabel label=new JLabel("Oopsi "+playerName+" ! Next time you will win !");
                		        //gbc.insets=new Insets(200,0,0,0);
                		        gbc.gridy=10;
                		        add(label,gbc);
                		        JButton retryButton=new JButton("  Retry  ");
                		    	//Font
                		    	Font customFont = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
                		    	retryButton.setFont(customFont);
                		    	retryButton.setBorder(BorderFactory.createEmptyBorder());
                		    	retryButton.setBackground(Color.pink);
                		    	//gbc.insets=new Insets(10,0,0,0);
                		    	gbc.gridy=11;
                		        add(retryButton,gbc);	    	
                	        }
            			}
            			
            		}
   
            	}
            }
        });
    }

    public void setEtape(int newStep) {
        step = newStep;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // Dessine le pendu partiel sur le JPanel
        Graphics2D g2d = (Graphics2D) g;
        drawHangMan(g2d, step); // Dessine l'étape actuelle du pendu
    }

    private void drawHangMan(Graphics g, int step) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED); // Changez la couleur ou les paramètres selon vos besoins

        switch (step) {
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
                Font boldFont = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(boldFont);
                g2d.drawString("You lost !", 120, 330); // Afficher "Vous avez perdu !"
                break;
                

        }
    }

}
