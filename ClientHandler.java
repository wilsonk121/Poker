import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Server server; // Reference to the Server instance

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket; // Store the Client instance
        this.server = server; // Store the Server instance
    }

    public String getPlayerName() {
        String playerName = null;

        try {
            // Set up input and output streams if not already done
            if (in == null) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // client reply
            }
            if (out == null) {
                out = new PrintWriter(socket.getOutputStream(), true); // response to client
            }

            // Loop until a valid player name is received
            while (playerName == null || playerName.trim().isEmpty()) {
                out.println("Enter your name:");
                playerName = in.readLine(); // Read the name from the client

                // Validate player name
                if (playerName == null || playerName.trim().isEmpty()) {
                    out.println("Invalid name. Please enter a non-empty name.");
                } else {
                    out.println("Welcome, " + playerName + "!"); // Acknowledge the name
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Log the error
        }

        return playerName; // Return the valid player name
    }

    public String getPlayerAction(List<Card> hand) {

        String Action = null;
        try {
            // Set up input and output streams if not already done
            if (in == null) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // client reply
            }
            if (out == null) {
                out = new PrintWriter(socket.getOutputStream(), true); // response to client
            }

            // Loop until a valid player name is received
            while (Action == null || Action.trim().isEmpty()) {
                out.println("Enter your action:");
                out.println("Your Card Is");
                out.println(hand);
                Action = in.readLine(); // Read the name from the client

                // Validate player name
                if (Action == null || Action.trim().isEmpty()) {
                    out.println("Invalid name. Please enter a non-empty name.");
                } else {
                    out.println("Welcome, " + Action + "!"); // Acknowledge the name
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Log the error
        }

        return Action; // Return the valid player name


    }

    // Handle actions from the player
    private void handlePlayerAction(Player player, String action) {
        switch (action.toLowerCase()) {
            case "bet":
                out.println("Enter the amount to bet:");
                try {
                    int betAmount = Integer.parseInt(in.readLine());
                    player.bet(betAmount); // Call the bet method
                } catch (NumberFormatException e) {
                    out.println("Invalid amount. Please enter a number.");
                } catch (IOException e) {
                    out.println("Error reading the bet amount.");
                }
                break;

            case "fold":
                player.fold(); // Call the fold method
                break;

            case "show hand":
                player.showHand(); // Display the player's hand
                break;

            default:
                out.println("Invalid action. Please choose again (bet, fold, show hand):");
                break;
        }
    }
}