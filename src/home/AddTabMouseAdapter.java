package Home;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.dropbox.core.json.JsonReader.FileLoadException;

public class AddTabMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	private HomePanel home;
	public AddTabMouseAdapter(HomePanel home) {
		this.home = home;
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
    	JFrame signInFrame = new SignInFrame(home);
    	signInFrame.setVisible(true);
    }
}