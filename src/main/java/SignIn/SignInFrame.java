package SignIn;

import javax.swing.*;

import Home.HomePanel;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SignInFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -376770814429213091L;

	public SignInFrame(HomePanel home) {
		super("Sign In");
		JPanel addTabFramePanel = new JPanel();
		addTabFramePanel.setLayout(new BoxLayout(addTabFramePanel, BoxLayout.Y_AXIS));

		ImageIcon dbxImg = createImageIcon("img/600DropboxIconWithName.png", home.tabSize);
		ImageIcon gglImg = createImageIcon("img/600GoogleDriveIconWithName.png", home.tabSize);
		ImageIcon oneDriveIcon = createImageIcon("img/600OneDriveWithName.png", home.tabSize);
		Insets inset = new Insets(10, 30, 10, 30);
		JButton connectToDbx = new JButton();
		connectToDbx.setMargin(inset);
		connectToDbx.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		connectToDbx.setAlignmentY(JComponent.CENTER_ALIGNMENT);

		connectToDbx.setIcon(dbxImg);
		connectToDbx.setName("DbxSignInButton");

		JButton connectToGgl = new JButton();
		connectToGgl.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		connectToGgl.setAlignmentY(JComponent.CENTER_ALIGNMENT);

		connectToGgl.setMargin(inset);
		connectToGgl.setIcon(gglImg);
		connectToGgl.setName("GglSignInButton");

		JButton connectToOneDrive = new JButton();
		connectToOneDrive.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		connectToOneDrive.setAlignmentY(JComponent.CENTER_ALIGNMENT);

		connectToOneDrive.setMargin(inset);
		connectToOneDrive.setIcon(oneDriveIcon);
		connectToOneDrive.setName("OneDriveSignInButton");
		
		connectToDbx.addMouseListener(new SignInButtonMouseAdapter(home, connectToDbx));
		connectToGgl.addMouseListener(new SignInButtonMouseAdapter(home, connectToGgl));
		connectToOneDrive.addMouseListener(new SignInButtonMouseAdapter(home, connectToOneDrive));

		addTabFramePanel.add(connectToDbx);
		addTabFramePanel.add(connectToGgl);
		addTabFramePanel.add(connectToOneDrive);

		addTabFramePanel.setBackground(Color.WHITE);
		addTabFramePanel.setPreferredSize(new Dimension(home.tabSize.width *2, (home.tabSize.height+25) * 3));

		add(addTabFramePanel);
		pack();
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