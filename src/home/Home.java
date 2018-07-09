package home;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Home extends JPanel {
	//private variables
	private static JTabbedPane cloudTabbedPane;
	public static Dimension fullLoginScreenSize;
	public static Dimension tabSize;
	private static int numTabs;
	// Constructor to setup the GUI components
	public Home() {
		super(new GridLayout(1,1));
		
		numTabs = 0;
		System.out.println(numTabs);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);
		tabSize = new Dimension(100, 28);
		cloudTabbedPane = new JTabbedPane(JTabbedPane.TOP);

		cloudTabbedPane.setUI(new BasicTabbedPaneUI());

		JComponent allPane = makeTextPanel("All panel");
		cloudTabbedPane.addTab("", allPane);
	    JLabel allPaneTitle = new JLabel("All");    // create a label
	    allPaneTitle.setHorizontalAlignment(JLabel.CENTER);
	    allPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(0, allPaneTitle);
		numTabs++;
		
		JComponent plusPane = makeTextPanel("Plus panel");
		cloudTabbedPane.addTab("", plusPane);
	    JLabel plusPaneTitle = new JLabel("+");    // create a label
	    plusPaneTitle.setHorizontalAlignment(JLabel.CENTER);
	    plusPaneTitle.addMouseListener(new AddTabMouseAdapter());
	    plusPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(1, plusPaneTitle);
		numTabs++;

		add(cloudTabbedPane);
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
//	}
	
	public static void addTab(JComponent body, ImageIcon icon) {
		System.out.println(numTabs);

		cloudTabbedPane.insertTab("", icon, body, "", numTabs-1);
		JLabel title = new JLabel();
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setIcon(icon);
		title.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(numTabs-1, title);

		numTabs++;
	}
	
	public static void resize() {
		
	}
	
	/**
	 * Helper function making text panel
	 * @param text
	 * @return
	 */
	public static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        panel.setPreferredSize(new Dimension(100,100));
        return panel;
    }

	/**
	 * Helper function creating image icon
	 * @param path path to that image
	 * @param size size of the image
	 * @return
	 */
    protected static ImageIcon createImageIcon(String path, Dimension size) {
    	Image img = Toolkit.getDefaultToolkit().getImage(path);
    	Image resizedImage = img.getScaledInstance(size.width-20, size.height-10, java.awt.Image.SCALE_REPLICATE);
        if (resizedImage != null) {
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
		
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        
        //Add content to the window.
        frame.add(new Home(), BorderLayout.CENTER);
        frame.add(new CopyRight(), BorderLayout.PAGE_END);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(fullLoginScreenSize);
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