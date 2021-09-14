package ServerAndClients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

        // Number a client
        private int clientNo = 0;


        public void StartTheServer() {

            new Thread( () -> {
                try {
                    // Create a server socket
                    ServerSocket serverSocket = new ServerSocket(4444);


                    while (true) {
                        // Listen for a new connection request
                        Socket socket = serverSocket.accept();

                        // Increment clientNo
                        clientNo++;

                        // Create and start a new thread for the connection
                        new Thread(new HandleAClient(socket)).start();
                    }
                }
                catch(IOException ex) {
                    System.err.println(ex);
                }
            }).start();
        }

        // Define the thread class for handling new connection
        static class HandleAClient implements Runnable {
            private Socket socket; // A connected socket

            /** Construct a thread */
            public HandleAClient(Socket socket) {
                this.socket = socket;
            }

            /** Run a thread */
            public void run() {
                try {
                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(
                            socket.getInputStream());
                    DataOutputStream outputToClient = new DataOutputStream(
                            socket.getOutputStream());

                    // Continuously serve the client
                    while (true) {
                        // Receive radius from the client
                        double radius = inputFromClient.readDouble();

                        // Compute area
                        double area = radius * radius * Math.PI;

                        // Send area back to the client
                        outputToClient.writeDouble(area);


                    }
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * The main method is only needed for the IDE with limited
         * JavaFX support. Not needed for running from the command line.
         */
        public static void main(String[] args) {
            MultiThreadServer multiThreadServer = new MultiThreadServer();
            multiThreadServer.StartTheServer();
        }
    }

