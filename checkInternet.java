import java.io.IOException;
import java.net.InetAddress;
public class checkInternet
{ 


 public static boolean isInternetAvailable() {
        {
        String host = "google.com"; 
        try {
            InetAddress address = InetAddress.getByName(host);
            if (address.isReachable(3000)) {  // Timeout: 3000ms
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
    }
}