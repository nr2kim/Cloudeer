/**
 * Example from https://github.com/dropbox/dropbox-sdk-java
 */
package SignIn;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader.FileLoadException;
import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.net.URI;
import java.net.URISyntaxException;

public class SignInDropbox extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6009433698203435290L;
	private static JPanel dbxPanel;
	private static JButton accessTokenCommit;
	private static JTextField accessTokenInput;
	private static JTextArea accessInfo;
	private static DbxWebAuth webAuth;
	private static DbxAppInfo appInfo;
	private static DbxRequestConfig config;
	
	public SignInDropbox() throws FileLoadException {
		super("Log In to Dropbox");
		dbxPanel = new JPanel(new GridBagLayout());
		accessTokenCommit = new JButton("Connect");
		accessTokenInput = new JTextField("Enter the authorization code here...");
		accessTokenInput.setPreferredSize(new Dimension(50*accessTokenInput.getFont().getSize(), accessTokenInput.getFont().getSize()*2));
		accessInfo = new JTextArea();
		accessInfo.setEditable(false);
		accessInfo.setOpaque(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getAccessTokenSetUp();
		add(dbxPanel);
		
		pack();
		setLocationRelativeTo(null);
	}

	@SuppressWarnings("deprecation")
	public static void getAccessTokenSetUp() throws FileLoadException {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		config = new DbxRequestConfig("Cloudeer/1.0.0", "en-CA");
		appInfo = DbxAppInfo.Reader.readFromFile("resources/dbx.app.env");
        webAuth = new DbxWebAuth(config, appInfo);
        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
            .withNoRedirect()
            .build();

        String authorizeUrl = webAuth.authorize(webAuthRequest);
        if (Desktop.isDesktopSupported()) {
            try {
				Desktop.getDesktop().browse(new URI(authorizeUrl));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        accessInfo.setText("1. Go to " + authorizeUrl + "\n2. Click \"Allow\" (you might have to log in first)."
        		+ "\n3. Copy the authorization code.");
		accessTokenCommit.addMouseListener(new AuthenticateDropboxMouseAdapter(accessTokenInput, accessTokenCommit, webAuth, appInfo));

        GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(40, 40, 20, 40);
		dbxPanel.add(accessInfo, constraints);
		constraints.insets = new Insets(20, 40, 20, 40);
		constraints.gridy = 30;
        dbxPanel.add(accessTokenInput, constraints);
        constraints.gridy = 60;
		constraints.insets = new Insets(20, 40, 40, 40);
        dbxPanel.add(accessTokenCommit, constraints);
	}
}