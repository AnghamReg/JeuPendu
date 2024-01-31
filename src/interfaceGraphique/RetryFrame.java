package interfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetryFrame extends JFrame {
    private String word;
    private JFrame mainFrame;

    public RetryFrame(String word, JFrame mainFrame) {
    	this.setTitle("Oopsi !");
        this.word = word;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(400,200));

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("You lost! The word was '" + word + "' , Maybe next time !");
        add(messageLabel, BorderLayout.CENTER);

        JButton retryButton = new JButton("Retry");
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the RetryFrame
                mainFrame.dispose(); // Close the main frame
                new MenuWindow();
            }
        });
        add(retryButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
