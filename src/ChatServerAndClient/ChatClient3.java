package ChatServerAndClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient3 {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        System.out.println("Tilslutter til localhost");
        var socket = new Socket("localhost", 4502);
        Scanner in = new Scanner(socket.getInputStream());
        Scanner name = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream(), true);
        String message;

        System.out.println("Indtast et navn");
        message = name.nextLine();
        out.println(message);


        new Thread(()->{
            try {
                while (true) {

                    System.out.println(in.nextLine());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

        ).start();

        while(true){
            sendMessage();
        }

    }
    public static void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Skriv en besked");
        String besked =  scanner.nextLine();
        out.println(besked);
    }
}