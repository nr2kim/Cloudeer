package SignIn;

import javax.swing.*;

import com.dropbox.core.json.JsonReader.FileLoadException;

import Home.HomePanel;
import Util.Util;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -376770814429213091L;

	public SignInFrame(HomePanel home) {
		super("Sign In");
		JPanel addTabFramePanel = new JPanel();
		addTabFramePanel.setLayout(new BoxLayout(addTabFramePanel, BoxLayout.Y_AXIS));

		ImageIcon dbxImg = Util.createImageIcon("img/600DropboxIconWithName.png", home.tabSize);
		ImageIcon gglImg = Util.createImageIcon("img/600GoogleDriveIconWithName.png", home.tabSize);
		ImageIcon oneDriveIcon = Util.createImageIcon("img/600OneDriveWithName.png", home.tabSize);
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
		
		connectToDbx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent dropboxBody = Util.makeTextPanel("Dropbox panel");
				ImageIcon dropboxIcon = Util.createImageIcon("img/600DropboxIconWithName.png", home.tabSize);
				home.addTab(dropboxBody, dropboxIcon);
				JFrame signInFrame;
				try {
					signInFrame = new SignInDropbox();
					signInFrame.setLocationRelativeTo(null);
					signInFrame.setVisible(true);
					signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (FileLoadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		connectToGgl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent googleDriveBody = Util.makeTextPanel("Google panel");
				ImageIcon googleDriveIcon = Util.createImageIcon("img/600GoogleDriveIconWithName.png", home.tabSize);
				home.addTab(googleDriveBody, googleDriveIcon);
			}
		});

		connectToOneDrive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent oneDriveBody = Util.makeTextPanel("One Drive panel");;
				ImageIcon oneDriveIcon = Util.createImageIcon("img/600OneDriveWithName.png", home.tabSize);
				home.addTab(oneDriveBody, oneDriveIcon);
			}
		});


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
}