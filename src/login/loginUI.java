package login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class loginUI extends JFrame {
	//private variables
	private JPanel loginPanel;
	private JButton connectToDbx;
	private JButton connectToGgl;
	private JButton connectToOneD;
	private JPanel copyRightPanel;
	public Dimension fullLoginScreenSize;
	// Constructor to setup the GUI components
	public loginUI() {
		super("Log In");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);
		loginPanel = new JPanel(new GridBagLayout());
		connectToDbx = new JButton("Login to Dropbox");
		connectToGgl = new JButton("Login to Google Drive");
		connectToOneD = new JButton("Login to One Drive");
		copyRightPanel = new JPanel(new GridBagLayout());
		pack();
		setSize(fullLoginScreenSize.width, fullLoginScreenSize.height);
		setLocationRelativeTo(null);
		// maybe work only for windows?
		setIconImage(Toolkit.getDefaultToolkit().getImage("../img/Cloudeer.png"));
		// put this for now, later, put it as JFrame.HIDE_ON_CLOSE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpConnectButtons();
		setUpCopyRightPanel();
		
		add(loginPanel);
		add(copyRightPanel);
		
	}
	
	//methods
	public void setUpConnectButtons() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(100,50,100,50);
		
		connectToDbx.setName("DbxSignInButton");
		connectToGgl.setName("GglSignInButton");
		connectToOneD.setName("OneDSignInButton");
		connectToDbx.addMouseListener(new connectButtonMouseAdapter(connectToDbx));
		connectToGgl.addMouseListener(new connectButtonMouseAdapter(connectToGgl));
		connectToOneD.addMouseListener(new connectButtonMouseAdapter(connectToOneD));
		
		// TODO:: SETUP IMAGES
		loginPanel.add(connectToDbx, constraints);
		loginPanel.add(connectToGgl, constraints);
		loginPanel.add(connectToOneD, constraints);
		
		loginPanel.setBackground(Color.WHITE);
		
		loginPanel.setSize(fullLoginScreenSize.width, fullLoginScreenSize.height * 9 / 10);
	}
	
	public void setUpCopyRightPanel() {
		copyRightPanel.setBackground(Color.LIGHT_GRAY);
		copyRightPanel.setSize(fullLoginScreenSize.height/9, fullLoginScreenSize.height/9);
		
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