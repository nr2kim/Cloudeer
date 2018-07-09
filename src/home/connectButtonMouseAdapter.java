package home;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.dropbox.core.json.JsonReader.FileLoadException;

import DbxLogin.SignInDbx;

public class connectButtonMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	JButton thisButton;

	public connectButtonMouseAdapter(JButton connectButton) {
		thisButton = connectButton;
	}
	
	@Override
    public void mousePressed(MouseEvent e) {
        thisButton.getModel().setArmed(true);
        thisButton.getModel().setPressed(true);
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //if(isRightButtonPressed) {underlyingButton.getModel().setPressed(true));
    	thisButton.getModel().setArmed(false);
    	thisButton.getModel().setPressed(false);

        if (pressed) {
            if (SwingUtilities.isLeftMouseButton(e)) {
            	try {
					addTabSetup();
				} catch (FileLoadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
        pressed = false;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        pressed = true;
    }
    
    private void addTabSetup() throws FileLoadException {
    	JFrame signInFrame;
    	switch (thisButton.getName()) {
		case "DbxSignInButton":
			JComponent dropboxBody = makeTextPanel("Dropbox panel");
			ImageIcon dropboxIcon = Home.createImageIcon("img/600DropboxIconWithName.png", Home.tabSize);
			Home.addTab(dropboxBody, dropboxIcon);
			signInFrame = new SignInDbx();
	        signInFrame.setLocationRelativeTo(null);
	        signInFrame.setVisible(true);
	        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case "GglSignInButton":
			JComponent googleDriveBody = makeTextPanel("Google panel");
			ImageIcon googleDriveIcon = Home.createImageIcon("img/600GoogleDriveIconWithName.png", Home.tabSize);
			Home.addTab(googleDriveBody, googleDriveIcon);
			break;
		case "OneDriveSignInButton":
			JComponent oneDriveBody = makeTextPanel("One Drive panel");;
			ImageIcon oneDriveIcon = Home.createImageIcon("img/600OneDriveWithName.png", Home.tabSize);
			Home.addTab(oneDriveBody, oneDriveIcon);
			
			break;
		default:
			break;
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
}