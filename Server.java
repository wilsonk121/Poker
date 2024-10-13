import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345; // Port for the server
    private List<Player> players;          // List of players in the game
    private Game game;                     // Game object
    private Map<Player, ClientHandler> playerClientMap = new HashMap<>(); // Map of players to their handlers

    public Server() {
        this.players = new ArrayList<>();  // Initialize the list of players
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept a client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle the new client connection in a separate thread
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                String playerName = clientHandler.getPlayerName(); // Get the player's name
                Player player = new Player(playerName, 100); // Create a new player

                // Map the player to the client handler
                playerClientMap.put(player, clientHandler);
                players.add(player); // Add player to the list of players

                System.out.println("Players: " + players);
                System.out.println("Number of players: " + players.size());

                if (players.size() == 3) { // Start the game when 3 players are connected
                    break;
                }
            }

            // Start the game
            Game game = new Game(players, 10,playerClientMap);
            game.startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Server server = new Server();
        server.start(); // Start the server
    }
}