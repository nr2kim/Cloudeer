package login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
		Image dbxImg = Toolkit.getDefaultToolkit().getImage("img/DropboxIconWithName.png");
		Image gglImg = Toolkit.getDefaultToolkit().getImage("img/GoogleDriveIconWithName.png");
		Image OneDImg = Toolkit.getDefaultToolkit().getImage("img/OneDriveIconWithName.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);
		loginPanel = new JPanel(new GridBagLayout());
		int iconWidth = fullLoginScreenSize.width/8;
		connectToDbx = new JButton();
		connectToDbx.setIcon(new ImageIcon(dbxImg.getScaledInstance(iconWidth, (int) (iconWidth*0.5), java.awt.Image.SCALE_SMOOTH)));
		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder(0, (int) (iconWidth*0.25), 0, (int) (iconWidth*0.25))));
		connectToGgl = new JButton();
		connectToGgl.setIcon(new ImageIcon(gglImg.getScaledInstance((int) (iconWidth*1.5), (int) (iconWidth*0.4), java.awt.Image.SCALE_SMOOTH)));
		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder((int) (iconWidth*0.05), 0, (int) (iconWidth*0.05), 0)));
		connectToOneD = new JButton();
		connectToOneD.setIcon(new ImageIcon(OneDImg.getScaledInstance((int) (iconWidth*1.4), (int) (iconWidth*0.5), java.awt.Image.SCALE_SMOOTH)));
		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder(0, (int) (iconWidth*0.05), 0, (int) (iconWidth*0.05))));
		
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