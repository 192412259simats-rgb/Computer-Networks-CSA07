import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            String host = "www.google.com";

            InetAddress address = InetAddress.getByName(host);

            System.out.println("Hostname   : " + host);
            System.out.println("IP Address : " + address.getHostAddress());

        } catch (UnknownHostException e) {
            System.out.println("Host not found.");
        }
    }
}
