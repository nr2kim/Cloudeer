/**
 * Example from https://github.com/dropbox/dropbox-sdk-java
 */
package DbxLogin;
import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader.FileLoadException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import login.DbxConnectButtonMouseAdapter;

import javax.swing.*;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class SignInDbx extends JFrame {
	private static JPanel dbxPanel;
	private static JButton accessTokenCommit;
	private static JTextField accessTokenInput;
	private static JTextArea accessInfo;
	private static DbxWebAuth webAuth;
	private static DbxAppInfo appInfo;
	
	public SignInDbx() throws FileLoadException {
		super("Log In to Dropbox");
		dbxPanel = new JPanel(new GridBagLayout());
		accessTokenCommit = new JButton("Connect");
		accessTokenInput = new JTextField("Enter the authorization code here...");
		accessInfo = new JTextArea();
		
		accessInfo.setEditable(false);
		accessInfo.setOpaque(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		accessTokenCommit.addMouseListener(new DbxConnectButtonMouseAdapter(accessTokenInput, accessTokenCommit, webAuth, appInfo));
		getAccessTokenSetUp();
		add(dbxPanel);
		pack();
		setLocationRelativeTo(null);
	}

	public static void getAccessTokenSetUp() throws FileLoadException {
		// DbxRequestConfig(String clientIdentifier) : clientIdentifier = "Name/Version"
		DbxRequestConfig config = new DbxRequestConfig("Cloudeer/1.0.0", "en-CA");
		appInfo = DbxAppInfo.Reader.readFromFile("src/login/auth.env");
        webAuth = new DbxWebAuth(config, appInfo);
        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
            .withNoRedirect()
            .build();

        String authorizeUrl = webAuth.authorize(webAuthRequest);
        accessInfo.setText("1. Go to " + authorizeUrl + "\n 2. Click \"Allow\" (you might have to log in first)."
        		+ "3. Copy the authorization code.");

        GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(100,100,100,100);
		dbxPanel.add(accessInfo, constraints);
        dbxPanel.add(accessTokenInput, constraints);
        dbxPanel.add(accessTokenCommit, constraints);
	}
}