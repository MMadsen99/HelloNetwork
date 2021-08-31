package ServerAndClients;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpFinder {

    public static void main(String[] args) {
        InetAddress address;
        String url = "localhost";
        try {
            address = InetAddress.getByName(url);
            System.out.println("IP address: " + address.toString());
            address = InetAddress.getLocalHost();
            System.out.println(address.toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
