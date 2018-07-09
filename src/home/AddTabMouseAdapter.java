package home;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.dropbox.core.json.JsonReader.FileLoadException;

import DbxLogin.SignInDbx;

public class AddTabMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	public AddTabMouseAdapter() {
	}
	
	@Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pressed) {
            if (SwingUtilities.isLeftMouseButton(e)) {
            	try {
					signInFrameSetup();
				} catch (FileLoadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
            }
        }
        pressed = false;

    }
    
    public void signInFrameSetup() throws FileLoadException {
    	JFrame addTabFrame = new AddTabFrame();
    	addTabFrame.setVisible(true);
    }
}