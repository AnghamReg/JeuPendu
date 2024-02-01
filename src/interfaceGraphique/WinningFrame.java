package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WinningFrame extends JFrame {
    private String word;
    private JFrame mainFrame;
    private String playerName;

    public WinningFrame(String word, JFrame mainFrame, String playerName) {
    	this.playerName=playerName;
    	this.setTitle("Yaay !");
        this.word = word;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(500,200));

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        
     // Load image from resources
        ImageIcon originalIcon = new ImageIcon(MenuWindow.class.getResource("/resources/win.png")); 
     // Resize the image
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust width and height
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);

        // Add the image label to the NORTH position
        add(imageLabel, BorderLayout.NORTH);

        // Add the image label to the NORTH position
        add(imageLabel, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel("<html><center><div style='text-align: center; vertical-align: middle;'>" +
                "  Congratulations " + playerName + "! You guessed the word: '" + word + "'</div></center></html>");  
        messageLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        add(messageLabel, BorderLayout.CENTER);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HangmanDrawingPanel.step=0;
                dispose(); // Close the WinningFrame
                mainFrame.dispose(); // Close the main frame
                new MenuWindow();
            }
        });
        add(playAgainButton, BorderLayout.SOUTH);
        
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
