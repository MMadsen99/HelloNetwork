package ServerAndClients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        // her ændrer jeg formattet tiden bliver vist på
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");

        //Her får jeg tiden fra det nuværende tidspunkt
        Date date = new Date(System.currentTimeMillis());

        // her laver jeg en ny serversocket med 3232 som port
        ServerSocket serverSocket = new ServerSocket(4444);

        //her venter serveren på en client
        Socket socket = serverSocket.accept();

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while(true) {
            output.writeUTF(format.format(date) + "\n");
        }


    }
}
