package DbxLogin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader.FileLoadException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class DbxConnectButtonMouseAdapter extends MouseAdapter {
	boolean pressed = false;
	static JTextField tokenInfo;
	JButton thisButton;
	static DbxWebAuth webAuth;
	static DbxAppInfo appInfo;
	public DbxConnectButtonMouseAdapter(JTextField accessTokenInfo, JButton dbxButton, DbxWebAuth givenAuth, DbxAppInfo applicationInfo) {
		tokenInfo = accessTokenInfo;
		thisButton = dbxButton;
		webAuth = givenAuth;
		appInfo = applicationInfo;
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
					finishDbxAuth();
				} catch (IOException e1) {
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
    
    public static void finishDbxAuth() throws IOException {
		String token = new String(tokenInfo.getText());
        DbxAuthFinish authFinish;
        try {
            authFinish = webAuth.finishFromCode(token);
        } catch (DbxException ex) {
            System.err.println("Error in DbxWebAuth.authorize: " + ex.getMessage());
            System.exit(1); return;
        }

        System.out.println("Authorization complete.");
        System.out.println("- User ID: " + authFinish.getUserId());
        System.out.println("- Account ID: " + authFinish.getAccountId());
        System.out.println("- Access Token: " + authFinish.getAccessToken());

        // Save auth information to output file.
        DbxAuthInfo authInfo = new DbxAuthInfo(authFinish.getAccessToken(), appInfo.getHost());
        File output = new File("./auth_output.txt");
        try {
            DbxAuthInfo.Writer.writeToFile(authInfo, output);
            System.out.println("Saved authorization information to \"" + output.getCanonicalPath() + "\".");
        } catch (IOException ex) {
            System.err.println("Error saving to <auth-file-out>: " + ex.getMessage());
            System.err.println("Dumping to stderr instead:");
            DbxAuthInfo.Writer.writeToStream(authInfo, System.err);
            System.exit(1); return;
        }

    }
}