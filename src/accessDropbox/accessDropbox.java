/**
 * Example from https://github.com/dropbox/dropbox-sdk-java
 */
package accessDropbox;
import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import java.util.List;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;

public class accessDropbox {
    private static final String ACCESS_TOKEN = "RGaKeZOu4WAAAAAAAAAbmTZAm_ooJGpWtPUwJqM7RjvlrYMzvIrxjwrEfEgXAuby";
    static DbxRequestConfig config = new DbxRequestConfig("example", "en_US");
    static DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
    public static void main(String args[]) throws DbxException, IOException {
    }
    public static void listFiles() throws DbxApiException, DbxException {
        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }    	
    }
    public static void uploadFile() throws UploadErrorException, DbxException, IOException {
        try (InputStream in = new FileInputStream("test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                .uploadAndFinish(in);
        }    	
    }
    public static void downloadFile(String fileName) throws DownloadErrorException, DbxException, IOException {
    	DbxDownloader<FileMetadata> downloader = client.files().download(fileName);
        try {
            FileOutputStream out = new FileOutputStream("test.txt");
            downloader.download(out);
            out.close();
        } catch (DbxException ex) {
            System.out.println(ex.getMessage());
        }
    }
}