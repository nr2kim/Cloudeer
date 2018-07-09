package home;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Home extends JPanel {
	//private variables
	private static JTabbedPane cloudTabbedPane;
	public static Dimension tabSize;
	private static int numTabs;
	// Constructor to setup the GUI components
	public Home() {
		super(new GridLayout(1,0));
		
		numTabs = 0;
		tabSize = new Dimension(100, 28);
		cloudTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		cloudTabbedPane.setAutoscrolls(true);

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
	}
	
	public static void addTab(JComponent body, ImageIcon icon) {
		cloudTabbedPane.insertTab("", icon, body, "", numTabs-1);

		JLabel title = new JLabel();
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setIcon(icon);
		title.setPreferredSize(tabSize);
		
		cloudTabbedPane.setTabComponentAt(numTabs-1, title);

		numTabs++;

//		if(numTabs > 5) {
//			resize();
//		}
	}
	
	public static void resize() {
		tabSize = new Dimension((Frame.fullLoginScreenSize.width-20)/numTabs, 28);
		for(int i = 0; i < numTabs; i++) {
			cloudTabbedPane.getComponentAt(i).setPreferredSize(tabSize);
		}
	}
	
	/**
	 * Helper function making text panel
	 * @param text
	 * @return
	 */
	public JComponent makeTextPanel(String text) {
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
}