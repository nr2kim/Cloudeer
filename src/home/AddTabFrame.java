/**
 * Example from https://github.com/dropbox/dropbox-sdk-java
 */
package Home;

import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;

public class AddTabFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -376770814429213091L;

	public AddTabFrame() {
		super("Sign In");
		JPanel addTabFramePanel = new JPanel();
		ImageIcon dbxImg = createImageIcon("img/600DropboxIconWithName.png", Home.tabSize);
		ImageIcon gglImg = createImageIcon("img/600GoogleDriveIconWithName.png", Home.tabSize);
		ImageIcon oneDriveIcon = createImageIcon("img/600OneDriveWithName.png", Home.tabSize);
		JButton connectToDbx = new JButton();
		connectToDbx.setIcon(dbxImg);
		connectToDbx.setName("DbxSignInButton");

		JButton connectToGgl = new JButton();
		connectToGgl.setIcon(gglImg);
		connectToGgl.setName("GglSignInButton");

		JButton connectToOneDrive = new JButton();
		connectToOneDrive.setIcon(oneDriveIcon);
		connectToOneDrive.setName("OneDriveSignInButton");
		
		connectToDbx.addMouseListener(new connectButtonMouseAdapter(connectToDbx));
		connectToGgl.addMouseListener(new connectButtonMouseAdapter(connectToGgl));
		connectToOneDrive.addMouseListener(new connectButtonMouseAdapter(connectToOneDrive));

		addTabFramePanel.add(connectToDbx);
		addTabFramePanel.add(connectToGgl);
		addTabFramePanel.add(connectToOneDrive);

		addTabFramePanel.setBackground(Color.WHITE);
		add(addTabFramePanel);
		pack();
//		setSize(fullLoginScreenSize.width, fullLoginScreenSize.height);
		setLocationRelativeTo(null);
		// put this for now, later, put it as JFrame.HIDE_ON_CLOSE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
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