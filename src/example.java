/**
 * Example from https://github.com/dropbox/dropbox-sdk-java
 */
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Main {
    private static final String ACCESS_TOKEN = "RGaKeZOu4WAAAAAAAAAbmTZAm_ooJGpWtPUwJqM7RjvlrYMzvIrxjwrEfEgXAuby";

    // can use curl command to obtain access token
    // curl <a class="vglnk" href="https://api.dropbox.com/1/account/info" rel="nofollow"><span>https</span><span>://</span><span>api</span><span>.</span><span>dropbox</span><span>.</span><span>com</span><span>/</span><span>1</span><span>/</span><span>account</span><span>/</span><span>info</span></a> -H "Authorization:Bearer <YOUR-ACCESS-TOKEN>"
    // refer : https://blogs.dropbox.com/developers/2014/05/generate-an-access-token-for-your-own-account/
    public static void main(String args[]) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    }
}