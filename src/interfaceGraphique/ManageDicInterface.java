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

public class ManageDicInterface extends JPanel {
	private JFrame manageDicWindow;
	
	public ManageDicInterface(JFrame mDicW) {
		this.manageDicWindow=new JFrame();
		this.manageDicWindow=mDicW;
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
                    // Add functionality for the add button here
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deleteWordTextField.getText().isEmpty()) {
                	JOptionPane.showMessageDialog(ManageDicInterface.this, "Delete word field cannot be empty.", "Error",
							JOptionPane.ERROR_MESSAGE);
                } else {
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
