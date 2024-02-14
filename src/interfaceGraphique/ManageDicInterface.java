package interfaceGraphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arbre.DicTree;

public class ManageDicInterface extends JPanel {
    private JFrame manageDicWindow;
    private DicTree dicTree;

    public ManageDicInterface(JFrame mDicW) {
        this.dicTree = new DicTree("");
        this.manageDicWindow = new JFrame();
        this.manageDicWindow = mDicW;
        setPreferredSize(new Dimension(400, 100));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setComponents();
    }

    public void setComponents() {
        setLayout(new GridLayout(2, 3));

        JLabel addWordLabel = new JLabel("Add word:");
        JTextField addWordTextField = new JTextField();
        JLabel deleteWordLabel = new JLabel("Delete word:");
        JTextField deleteWordTextField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWordTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ManageDicInterface.this, "Add word field cannot be empty.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String word = addWordTextField.getText().toLowerCase();
                    if (dicTree.wordExistsInFile(word)) {
                        JOptionPane.showMessageDialog(ManageDicInterface.this, "this word already exist !", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean isAdded = dicTree.addWordToFile(word);
                    if (isAdded) {
                        JOptionPane.showMessageDialog(ManageDicInterface.this,
                                "your word has been added successfully!!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    // Add functionality for the add button here
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deleteWordTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ManageDicInterface.this, "Delete word field cannot be empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String word = deleteWordTextField.getText().toLowerCase();
                    System.out.println(word);
                    if (!dicTree.wordExistsInFile(word)) {
                        JOptionPane.showMessageDialog(ManageDicInterface.this, "this word does not exist !", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean isDeleted = false;
                    try {
                        isDeleted = dicTree.deleteWordFromFile(word);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(ManageDicInterface.this,
                                "your word has been deleted successfully!!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    // Add functionality for the delete button here
                }
            }
        });

        add(addWordLabel);
        add(addWordTextField);
        add(addButton);
        add(deleteWordLabel);
        add(deleteWordTextField);

        add(deleteButton);
    }

}
