import java.io.*;
import java.net.*;

public class SimpleTCPClient {
    public static void main(String[] args) {
        final BufferedReader userIn;
        final PrintWriter out;
        final BufferedReader in;
        Socket socket = null;

        try {
            socket = new Socket("localhost", 6666);
            userIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to the server. Type messages:");

            Thread sendThread = new Thread(() -> {
                try {
                    String userInput;
                    while ((userInput = userIn.readLine()) != null) {
                        out.println(userInput);
                    }
                } catch (IOException e) {
                    System.err.println("Error sending message: " + e.getMessage());
                }
            });

            Thread receiveThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error receiving message: " + e.getMessage());
                }
            });

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
