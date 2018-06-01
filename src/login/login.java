package login;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.json.JsonReader.FileLoadException;
import com.dropbox.core.DbxRequestUtil;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxWebAuth;
// dropbox login through OAuth2 : https://oauth.net/2/grant-types/password/
// 			for expired token	: https://oauth.net/2/grant-types/refresh-token/
public class login {
	public static void main(String[] args) throws FileLoadException {
		dropboxLogin("", ""); 
	}
	public static void dropboxLogin(String username, String password) throws FileLoadException {
		//grant_type('password');
		DbxRequestConfig config = new DbxRequestConfig("dn1ejzxezzcblo0", "en_US");
		DbxRequestUtil reqUtil;
		DbxAppInfo appInfo = DbxAppInfo.Reader.readFromFile("src/login/auth.env");
		DbxWebAuth auth = new DbxWebAuth(config, appInfo);
		DbxWebAuth.Request authRequest = DbxWebAuth.newRequestBuilder()
			.withNoRedirect()
			.build();
		String authorizeUrl = auth.authorize(authRequest);
		// Todo:: this is using web ; let's try not to use the web version, do it behind - backend
		System.out.println("1. Go to " + authorizeUrl);
	     System.out.println("2. Click \"Allow\" (you might have to log in first).");
	     System.out.println("3. Copy the authorization code.");
	     System.out.print("Enter the authorization code here: ");
		// reqUtil.buildUrlWithParams("en_US", "https://www.dropbox.com", "/oauth2/authorize", param);
		// Todo:: put app key inside .env file
	}
}