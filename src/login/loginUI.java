package login;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;

public class loginUI extends JFrame {
	//private variables
	private JButton loginButton = new JButton("Login to Dropbox");
	
	// Constructor to setup the GUI components
	public loginUI() {
		super("Log In");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10,10,10,10);
		mainPanel.add(loginButton, constraints);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel"));
//		mainPanel.add(logo, constraints);
		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}
	
	//methods
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	public static void main(String[] args) {
		// set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new loginUI().setVisible(true);
            }
        });
	}
}