package interfaceGraphique;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ManageDicWindow  extends JFrame{
	private ManageDicInterface manageDicInterface;

	
	public ManageDicWindow() {
		this.setTitle("Manage your dictionnary");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    dispose();
                
            }
        });
		manageDicInterface=new ManageDicInterface(this);
		this.add(manageDicInterface);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
