package BmiServerAndClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BmiServer {



    public static void main(String[] args) {

        double bmi;
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Accepting connection on port: 5555");

            Socket socket = serverSocket.accept();
            System.out.println("Connection estabilished from " + socket.getRemoteSocketAddress());

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while(true) {
                String weight = inputStream.readUTF();
                String height = inputStream.readUTF();
                System.out.println("weight Data from client: " + weight);
                System.out.println("height Data from client: " + height);
                bmi = Double.parseDouble(weight) / Math.pow(Double.parseDouble(height),2);
                outputStream.writeUTF("Your bmi is: " + bmi);
                outputStream.flush();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
