package Home;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.dropbox.core.json.JsonReader.FileLoadException;

import SignIn.SignInFrame;
import Util.Util;

public class HomePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3269305306013516872L;
	//private variables
	private JTabbedPane cloudTabbedPane;
	public Dimension tabSize;
	private int numTabs;
	// Constructor to setup the GUI components
	public HomePanel() {
		super(new GridLayout(1,0));
//		UIManager.put("cloudTabbedPane.unselectedTabBackground", Color.decode("#B6F4FF"));
		
		numTabs = 0;
		tabSize = new Dimension(100, 28);
		cloudTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		cloudTabbedPane.setAutoscrolls(true);


		JComponent allPane = Util.makeTextPanel("All panel");
		cloudTabbedPane.addTab("", allPane);
	    JLabel allPaneTitle = new JLabel("All");    // create a label
	    allPaneTitle.setHorizontalAlignment(JLabel.CENTER);
	    allPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(0, allPaneTitle);
		numTabs++;
		
		JComponent plusPane = Util.makeTextPanel("Plus panel");
		cloudTabbedPane.addTab("", plusPane);
	    JLabel plusPaneTitle = new JLabel("+");    // create a label
	    plusPaneTitle.setHorizontalAlignment(JLabel.CENTER);
	    plusPaneTitle.addMouseListener(new MouseAdapter() {
    		boolean pressed = false;

    		@Override
    	    public void mousePressed(MouseEvent e) {
    	        pressed = true;
    	    }

    	    @Override
    	    public void mouseReleased(MouseEvent e) {
    	        if (pressed && SwingUtilities.isLeftMouseButton(e)) {
					try {
						signInFrameSetup();
					} catch (FileLoadException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	        }
    	        pressed = false;
    	    }
	    });

	    plusPaneTitle.setPreferredSize(tabSize);
		cloudTabbedPane.setTabComponentAt(1, plusPaneTitle);
		numTabs++;

		add(cloudTabbedPane);
		cloudTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setBackground(Color.WHITE);

	}

	public void signInFrameSetup() throws FileLoadException {
    	JFrame signInFrame = new SignInFrame(this);
    	signInFrame.setVisible(true);
    }

	public void addTab(JComponent body, ImageIcon icon) {
		cloudTabbedPane.insertTab("", icon, body, "", numTabs-1);

		JLabel title = new JLabel();
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setIcon(icon);
		title.setPreferredSize(tabSize);
		
		cloudTabbedPane.setTabComponentAt(numTabs-1, title);

		numTabs++;

		// this.resize(HomeSetup.homeFrameSize);
	}
	
	@Override
	public void resize(Dimension screenSize) {
		if(numTabs < 7) return;
		// TODO:: resize
//		tabSize = new Dimension((screenSize.width-20)/numTabs, 28);
//		for(int i = 0; i < numTabs; i++) {
//			 cloudTabbedPane.getIconAt(i);
//		}
	}
}