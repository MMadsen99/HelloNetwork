package ServerAndClients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.SyncFailedException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            //ny socket på localhost med port 4444
            Socket socket = new Socket("localhost",4444);

            // Data ind og ud af serveren
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());


            Scanner s = new Scanner(System.in);
            //while loop som gør at serveren kan modtage data fra client flere gange
            while(true) {
                outputStream.writeUTF(s.nextLine());
                outputStream.flush();
                System.out.println(inputStream.readUTF());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
