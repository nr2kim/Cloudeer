package home;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
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
            	thisButton.setText("F");
            	try {
					signInFrameSetup();
				} catch (FileLoadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
            	thisButton.setText("X");
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
    
    public void signInFrameSetup() throws FileLoadException {
    	JFrame signInFrame;
    	switch (thisButton.getName()) {
		case "DbxSignInButton":
			signInFrame = new SignInDbx();
	        signInFrame.setLocationRelativeTo(null);
	        signInFrame.setVisible(true);
	        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case "GglSignInButton":
			break;
		case "OneDSignInButton":
			System.out.println("Oneeeee Drive!");
		default:
			break;
		}
    }
}