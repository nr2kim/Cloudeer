package SignIn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.dropbox.core.json.JsonReader.FileLoadException;

import Home.HomePanel;
import Util.Util;

public class SignInButtonMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	JButton thisButton;
	HomePanel home;

	public SignInButtonMouseAdapter(HomePanel home, JButton connectButton) {
		thisButton = connectButton;
		this.home = home;
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
			JComponent dropboxBody = Util.makeTextPanel("Dropbox panel");
			ImageIcon dropboxIcon = Util.createImageIcon("img/600DropboxIconWithName.png", home.tabSize);
			home.addTab(dropboxBody, dropboxIcon);
			signInFrame = new SignInDropbox();
	        signInFrame.setLocationRelativeTo(null);
	        signInFrame.setVisible(true);
	        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case "GglSignInButton":
			JComponent googleDriveBody = Util.makeTextPanel("Google panel");
			ImageIcon googleDriveIcon = Util.createImageIcon("img/600GoogleDriveIconWithName.png", home.tabSize);
			home.addTab(googleDriveBody, googleDriveIcon);
			break;
		case "OneDriveSignInButton":
			JComponent oneDriveBody = Util.makeTextPanel("One Drive panel");;
			ImageIcon oneDriveIcon = Util.createImageIcon("img/600OneDriveWithName.png", home.tabSize);
			home.addTab(oneDriveBody, oneDriveIcon);
			
			break;
		default:
			break;
		}
    }
}