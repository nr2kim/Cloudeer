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

import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.net.URI;
import java.net.URISyntaxException;

public class SignInDbx extends JFrame {
	private static JPanel dbxPanel;
	private static JButton accessTokenCommit;
	private static JTextField accessTokenInput;
	private static JTextArea accessInfo;
	private static DbxWebAuth webAuth;
	private static DbxAppInfo appInfo;
	private static DbxRequestConfig config;
	
	public SignInDbx() throws FileLoadException {
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

	public static void getAccessTokenSetUp() throws FileLoadException {
		JSONParser parser = new JSONParser();
		Object parsedConfig = null;
		Object parsedAppInfo = null;
		try {
			parsedConfig = parser.parse(new FileReader("./src/dbx.cloudeer.config"));
			parsedAppInfo = parser.parse(new FileReader("./src/auth.env"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject dbxConfigJson = (JSONObject) parsedConfig;
		JSONObject dbxAppInfoJson = (JSONObject) parsedAppInfo;
		// DbxRequestConfig(String clientIdentifier) : clientIdentifier = "Name/Version"
		config = new DbxRequestConfig((String) dbxConfigJson.get("clientIdentifier"), (String) dbxConfigJson.get("userLocale"));
		appInfo = new DbxAppInfo((String) dbxAppInfoJson.get("key"), (String) dbxAppInfoJson.get("secret"));
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
		accessTokenCommit.addMouseListener(new DbxConnectButtonMouseAdapter(accessTokenInput, accessTokenCommit, webAuth, appInfo));

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