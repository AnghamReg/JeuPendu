package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinningFrame extends JFrame {
    private String word;
    private JFrame mainFrame;

    public WinningFrame(String word, JFrame mainFrame) {
    	this.setTitle("Yaay !");
        this.word = word;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(400,200));

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Congratulations! You guessed the word: '" + word + "'");
        add(messageLabel, BorderLayout.CENTER);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the WinningFrame
                mainFrame.dispose(); // Close the main frame
                new MenuWindow();
            }
        });
        add(playAgainButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

}
