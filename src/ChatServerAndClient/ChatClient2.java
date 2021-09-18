package ChatServerAndClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient2 {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        //laver en ny socket som køre op localhost med port 4502
        System.out.println("Tilslutter til localhost");
        var socket = new Socket("localhost", 4502);
        //laver nye input streams, data til og fra serveren
        Scanner in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        //brugern skriver et navn som bliver sendt til serveren
        Scanner name = new Scanner(System.in);
        String message;
        System.out.println("Indtast et navn");
            message = name.nextLine();
            out.println(message);

        //En thread som står og venter på nye beskeder fra andre clienter igennem serveren
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
        //While loop som kørere igennenm sendMessage metoden, så man kan sende så mange beskeder man ville
        while(true){
        sendMessage();
        }

}   //metode som tager input fra brugeren og sende det til severen
    public static void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Skriv en besked");
        String besked =  scanner.nextLine();
        out.println(besked);
    }
}