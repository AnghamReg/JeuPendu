package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RetryFrame extends JFrame {
    private String word;
    private JFrame mainFrame;
    private String playerName;

    public RetryFrame(String word, JFrame mainFrame, String playerName) {
    	this.playerName=playerName;
    	this.setTitle("Oopsi !");
        this.word = word;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(400,200));

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

     // Load image from resources
        ImageIcon originalIcon = new ImageIcon(MenuWindow.class.getResource("/resources/lose.png")); 
     // Resize the image
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust width and height
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);

        // Add the image label to the NORTH position
        add(imageLabel, BorderLayout.NORTH);
        
        JLabel messageLabel = new JLabel("Sorry " +playerName+" you lost! The word was '" + word + "' , Maybe next time !");
        add(messageLabel, BorderLayout.CENTER);

        JButton retryButton = new JButton("Retry");
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HangmanDrawingPanel.step=0;
                dispose(); // Close the RetryFrame
                mainFrame.dispose(); // Close the main frame
                new MenuWindow();
            }
        });
        add(retryButton, BorderLayout.SOUTH);
        
        addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the game?", "Confirm Close", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    HangmanDrawingPanel.step = 0;
                    dispose(); // Close the RetryFrame
                    mainFrame.dispose(); // Close the main frame
                }
            }
        });

        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
