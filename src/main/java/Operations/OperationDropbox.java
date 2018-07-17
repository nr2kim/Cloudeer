package Operations;
import javax.swing.JFrame;

public class OperationDropbox extends JFrame {
	private static final long serialVersionUID = -4141131976143917767L;

	public static void onAuthenticated() {
//		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
//
//        // Get current account info
//        FullAccount account = client.users().getCurrentAccount();
//        System.out.println(account.getName().getDisplayName());
//
//        // Get files and folder metadata from Dropbox root directory
//        ListFolderResult result = client.files().listFolder("");
//        while (true) {
//            for (Metadata metadata : result.getEntries()) {
//                System.out.println(metadata.getPathLower());
//            }
//
//            if (!result.getHasMore()) {
//                break;
//            }
//
//            result = client.files().listFolderContinue(result.getCursor());
//        }
//
//        // Upload "test.txt" to Dropbox
//        try (InputStream in = new FileInputStream("test.txt")) {
//            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
//                .uploadAndFinish(in);
//        }
	}	
}
