import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost"; // Change if needed
    private static final int SERVER_PORT = 12345; // Port must match the server's port
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // Connect to the server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to the server.");

            // Start the client interaction
            startInteraction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startInteraction() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Read welcome message and player name prompt


            String response = in.readLine();

            if (response != null) {
                System.out.println("HIHI");
                System.out.println(response); // Display server's message
                String playerName = scanner.nextLine(); // Send player name to the server
                out.println(playerName);
            } else {
                System.out.println("Server disconnected or sent an invalid response.");
                return;
            }

            // Game interaction loop
            System.out.println(response);
            // wait for next action
            String action=null;
            while (true) {
                response = in.readLine(); // Read server's response
                if(response != null){
                    System.out.println(response);
                    action = scanner.nextLine(); // Send player name to the server
                    out.println(action);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (socket != null) {
                    socket.close();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client(); // Start the client
    }
}