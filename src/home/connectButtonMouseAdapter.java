package home;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.dropbox.core.json.JsonReader.FileLoadException;

import DbxLogin.SignInDbx;

public class connectButtonMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	JButton thisButton;
	private JComponent dropboxBody;
	private JComponent googleDriveBody;
	private JComponent oneDriveBody;
	private ImageIcon dropboxIcon;
	private ImageIcon googleDriveIcon;
	private ImageIcon oneDriveIcon;
	public connectButtonMouseAdapter(JButton connectButton) {
		thisButton = connectButton;

		dropboxBody = Home.makeTextPanel("Dropbox panel");
		dropboxIcon = Home.createImageIcon("img/600DropboxIconWithName.png", Home.tabSize);
		googleDriveBody = Home.makeTextPanel("Google panel");
		googleDriveIcon = Home.createImageIcon("img/600GoogleDriveIconWithName.png", Home.tabSize);
		oneDriveBody = Home.makeTextPanel("One Drive panel");
		oneDriveIcon = Home.createImageIcon("img/600OneDriveWithName.png", Home.tabSize);
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
			Home.addTab(dropboxBody, dropboxIcon);
			signInFrame = new SignInDbx();
	        signInFrame.setLocationRelativeTo(null);
	        signInFrame.setVisible(true);
	        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case "GglSignInButton":
			Home.addTab(googleDriveBody, googleDriveIcon);
			break;
		case "OneDriveSignInButton":
			Home.addTab(oneDriveBody, oneDriveIcon);
			break;
		default:
			break;
		}
    }
}