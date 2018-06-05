package login;

import com.dropbox.core.DbxRequestConfig;
import java.awt.Button;
import java.awt.Panel;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.json.JsonReader.FileLoadException;
import com.dropbox.core.DbxRequestUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.v2.DbxClientV2;
// dropbox login through OAuth2 : https://oauth.net/2/grant-types/password/
// 			for expired token	: https://oauth.net/2/grant-types/refresh-token/
public class login {
	public static void main(String[] args) throws FileLoadException, DbxException, IOException {
		Panel pnl = new Panel();
		Button btn = new Button("Login To Dropbox");
		pnl.add(btn);
		dropboxLogin("", ""); 
	}
	public static void dropboxLogin(String username, String password) throws FileLoadException, DbxException, IOException {
		//grant_type('password');
		// DbxRequestConfig(String clientIdentifier) : clientIdentifier = "Name/Version"
		DbxRequestConfig config = new DbxRequestConfig("Cloudeer/1.0.0", "en-CA");
		DbxAppInfo appInfo = DbxAppInfo.Reader.readFromFile("src/login/auth.env");
        DbxWebAuth webAuth = new DbxWebAuth(config, appInfo);
        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
            .withNoRedirect()
            .build();

        String authorizeUrl = webAuth.authorize(webAuthRequest);
        System.out.println("1. Go to " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first).");
        System.out.println("3. Copy the authorization code.");
        System.out.print("Enter the authorization code here: ");

        String code = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (code == null) {
            System.exit(1); return;
        }
        code = code.trim();

        DbxAuthFinish authFinish;
        try {
            authFinish = webAuth.finishFromCode(code);
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
		// reqUtil.buildUrlWithParams("en_US", "https://www.dropbox.com", "/oauth2/authorize", param);
		// Todo:: put app key inside .env file