package ServerAndClients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args){

        int port = 4444;

        new Thread( () -> {
        try {
            //Trin 1 - lav en serverSocket med en port
            ServerSocket serverSocket = new ServerSocket(port);


        //Trin 2 - få en socket til at lytte
        System.out.println("Accepting connection on port: " + port);

        Socket socket = serverSocket.accept();

        System.out.println("Connection estabilished from " + socket.getRemoteSocketAddress().toString());

        //Trin 3 - Data ind og ud af serveren

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        //Trin 4 - her sender og modtager jeg data
        while(true) {
            String incomingText = inputStream.readUTF();
            System.out.println("Data from client: " + incomingText);
            outputStream.writeUTF(incomingText);
            outputStream.flush();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
        }).start();
    }




}
