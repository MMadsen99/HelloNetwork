package ChatServerAndClient;


// import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        //Her spørger vi brugeren om hviklen IP han ville joine, hvis han tykker ENTER tilslutter han localhost
        System.out.println("Hvilken server vil du tilslutte dig? (ENTER for localhost)");
        Scanner scanner = new Scanner(System.in);
        String serverString = scanner.nextLine();
        if(serverString.equals(""))serverString = "localhost";

        System.out.println("tilslutter til " + serverString);

        // her laver vi en socket som har ip og port
        var socket = new Socket(serverString,4502);
        //her laver vi in og output streams, output har autoflush enable
        Scanner in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(),true);

       /*While loop som venter på input fra serveren,(omkring linje 72) giver den os "SUBMITNAME"
           Hvis line starter med "SUBMITNAME" fra serveren går vi ind i if sætningen og så bliver vi spurgt om vores navn
            som så bliver sendt til serveren
        */
        while(in.hasNextLine()){
            var line = in.nextLine();
            if (line.startsWith("SUBMITNAME")){
                System.out.println("Skriv dit navn");
                String mitNavn = scanner.nextLine();
                //Her sender vi navnet tilbage til serveren
                out.println(mitNavn);
                /*hvis line starter med "NAMEACCEPTED" fra serveren, så Springer den 13 første index over og
                så kun giver os navnet( linje 88 ChatServer) */
            }else if (line.startsWith("NAMEACCEPTED")){
                System.out.println("Chatter - " + line.substring(13));
                sendMessage();
                //Den souter hvem som er joinet og derefter kalder sendMessage metoden
            }else if (line.startsWith("MESSAGE")){
                System.out.println(line.substring(8) + "\n");
                sendMessage();
            }


        }
    }
    //får besked fra scanner og sender beskeden til serveren
    public static void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv en besked");
        String besked =  scanner.nextLine();
        out.println(besked);

    }

}