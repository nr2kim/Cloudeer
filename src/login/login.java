package login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class login extends JPanel {
	//private variables
	private JTabbedPane cloudTabbedPane;
	private JButton connectToDbx;
	private JButton connectToGgl;
	private JButton connectToOneD;
	private JPanel copyRightPanel;
	public Dimension fullLoginScreenSize;
	private Dimension tabSize;
	private int numTabs;
	// Constructor to setup the GUI components
	public login() {
		super(new GridLayout(1,1));
		numTabs = 4;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);
		tabSize = new Dimension((fullLoginScreenSize.height-10)/numTabs, 50);
		cloudTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
			    
		JComponent allPane = makeTextPanel("All panel");
		ImageIcon allPaneIcon = createImageIcon("./img/Cloudeer.png", tabSize);
		cloudTabbedPane.addTab("", allPane);
	    JLabel allPaneTitle = new JLabel("All");    // create a label
	    allPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(0, allPaneTitle);
		cloudTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent dbxPane = makeTextPanel("Dropbox panel");
		ImageIcon dbxPaneIcon = createImageIcon("img/600DropboxIconWithName.png", tabSize);
		cloudTabbedPane.addTab("", dbxPane);
		JLabel dbxPaneTitle = new JLabel();
		dbxPaneTitle.setIcon(dbxPaneIcon);
		dbxPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(1, dbxPaneTitle);
		cloudTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent gglPane = makeTextPanel("Google panel");
		ImageIcon gglPaneIcon = createImageIcon("img/600GoogleDriveIconWithName.png", tabSize);
		cloudTabbedPane.addTab("", gglPaneIcon, gglPane);
		cloudTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		JComponent OneDrivePane = makeTextPanel("One Drive panel");
		ImageIcon OneDrivePaneIcon = createImageIcon("img/600OneDriveWithName.png", tabSize);
		cloudTabbedPane.addTab("", OneDrivePaneIcon, OneDrivePane);
		cloudTabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		
		
	    
		add(cloudTabbedPane);
		setPreferredSize(fullLoginScreenSize);
		cloudTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
//		Image dbxImg = Toolkit.getDefaultToolkit().getImage("img/DropboxIconWithName.png");
//		Image gglImg = Toolkit.getDefaultToolkit().getImage("img/GoogleDriveIconWithName.png");
//		Image OneDImg = Toolkit.getDefaultToolkit().getImage("img/OneDriveIconWithName.png");
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);
//		loginPanel = new JPanel(new GridBagLayout());
//		int iconWidth = fullLoginScreenSize.width/8;
//		connectToDbx = new JButton();
//		connectToDbx.setIcon(new ImageIcon(dbxImg.getScaledInstance(iconWidth, (int) (iconWidth*0.5), java.awt.Image.SCALE_SMOOTH)));
//		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
//		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder(0, (int) (iconWidth*0.25), 0, (int) (iconWidth*0.25))));
//		connectToGgl = new JButton();
//		connectToGgl.setIcon(new ImageIcon(gglImg.getScaledInstance((int) (iconWidth*1.5), (int) (iconWidth*0.4), java.awt.Image.SCALE_SMOOTH)));
//		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
//		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder((int) (iconWidth*0.05), 0, (int) (iconWidth*0.05), 0)));
//		connectToOneD = new JButton();
//		connectToOneD.setIcon(new ImageIcon(OneDImg.getScaledInstance((int) (iconWidth*1.4), (int) (iconWidth*0.5), java.awt.Image.SCALE_SMOOTH)));
//		connectToDbx.setBorder(BorderFactory.createCompoundBorder(
//		        BorderFactory.createLineBorder(Color.DARK_GRAY, 1), new EmptyBorder(0, (int) (iconWidth*0.05), 0, (int) (iconWidth*0.05))));
//		
//		copyRightPanel = new JPanel(new GridBagLayout());
//		pack();
//		setSize(fullLoginScreenSize.width, fullLoginScreenSize.height);
//		setLocationRelativeTo(null);
//		// put this for now, later, put it as JFrame.HIDE_ON_CLOSE
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setUpConnectButtons();
//		setUpCopyRightPanel();
//		
//		add(loginPanel);
//		add(copyRightPanel);
//		
	}
	
//	//methods
//	public void setUpConnectButtons() {
//		GridBagConstraints constraints = new GridBagConstraints();
//		constraints.anchor = GridBagConstraints.EAST;
//		constraints.insets = new Insets(100,50,100,50);
//		
//		connectToDbx.setName("DbxSignInButton");
//		connectToGgl.setName("GglSignInButton");
//		connectToOneD.setName("OneDSignInButton");
//		connectToDbx.addMouseListener(new connectButtonMouseAdapter(connectToDbx));
//		connectToGgl.addMouseListener(new connectButtonMouseAdapter(connectToGgl));
//		connectToOneD.addMouseListener(new connectButtonMouseAdapter(connectToOneD));
//		
//		// TODO:: SETUP IMAGES
//		loginPanel.add(connectToDbx, constraints);
//		loginPanel.add(connectToGgl, constraints);
//		loginPanel.add(connectToOneD, constraints);
//		
//		loginPanel.setBackground(Color.WHITE);
//		
//		loginPanel.setSize(fullLoginScreenSize.width, fullLoginScreenSize.height * 9 / 10);
//	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        panel.setPreferredSize(new Dimension(100,100));
        return panel;
    }

	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path, Dimension size) {
    	Image img = Toolkit.getDefaultToolkit().getImage(path);
    	Image resizedImage = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_REPLICATE);
        if (resizedImage != null) {
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	public void setUpCopyRightPanel() {
		copyRightPanel.setBackground(Color.LIGHT_GRAY);
		copyRightPanel.setSize(fullLoginScreenSize.height/9, fullLoginScreenSize.height/9);
		
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add content to the window.
        frame.add(new login(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}