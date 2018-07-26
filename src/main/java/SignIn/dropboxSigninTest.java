package SignIn;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader.FileLoadException;


public class dropboxSigninTest extends JFrame {
    private static JPanel dbxPanel;
	private static JButton accessTokenCommit;
	private static JTextArea accessInfo;
	private static DbxWebAuth webAuth;
	private static DbxAppInfo appInfo;
	private static DbxRequestConfig config;
	
	public dropboxSigninTest() throws FileLoadException, IOException {
		super("Log In to Dropbox");
        
//		dbxPanel = new JPanel(new GridBagLayout());
//		accessTokenCommit = new JButton("Connect");
//		JTextField ID = new JTextField("ID here");
//		JTextField password = new JTextField("password here");
//		ID.setPreferredSize(new Dimension(50*ID.getFont().getSize(), ID.getFont().getSize()*2));
//		password.setPreferredSize(new Dimension(50*password.getFont().getSize(), password.getFont().getSize()*2));
//		accessTokenCommit.addMouseListener(new AuthTestDropbox(ID, password));

//		StringBuilder result = new StringBuilder();
//		URL url = new URL("https://www.dropbox.com/oauth2/authorize?client_id=dn1ejzxezzcblo0&response_type=code");
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		String line;
//		while ((line = rd.readLine()) != null) {
//			result.append(line);
//		}
//		Document doc = kit.createDefaultDocument();
//        jEditorPane.setDocument(doc);
//        jEditorPane.setText(result.toString());
//		rd.close();
  
//		    HttpPost request = new HttpPost("https://www.dropbox.com/oauth2/authorize?client_id=dn1ejzxezzcblo0&response_type=code");
//		    HttpResponse response = httpClient.execute(request);
//		    System.out.println(response);

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
	}
}