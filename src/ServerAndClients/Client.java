package ServerAndClients;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
            //ny socket på localhost med port 4444
            Socket socket = new Socket("10.200.130.31",4501);
            // Data ind og ud af serveren
            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
           // DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        System.out.println("test");

               // String incomingText = inputStream.readUTF();
                System.out.print(inputStream.readUTF());
                System.out.print(inputStream.readUTF());
                System.out.println("vi er her nu");


    }
}
