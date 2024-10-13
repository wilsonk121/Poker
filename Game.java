import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private Deck deck;                  // Represents the deck of cards
    private List<Player> playerList;    // List of players in the game
    private List<Card> communityCards;   // Cards that are shared among players
    private int stake;                  // The stake for the current round
    private Player dealer;               // The dealer of the game
    private int bigBlind;                // The big blind amount
    private int pool;
    private Map<Player, ClientHandler> playerClientMap;



    // Constructor to initialize the game
    public Game(List<Player> players, int bigBlind, Map<Player, ClientHandler> playerClientMap) {
        this.deck = new Deck();          // Create a new deck of cards
        this.playerList = players;       // Initialize the list of players
        this.communityCards = new ArrayList<>(); // Initialize the community cards
        this.bigBlind = bigBlind;         // Set the big blind value
        this.playerClientMap = playerClientMap;
        dealer = playerList.get(0);
        stage = null;

    }

    // Method to start the game
    public void startGame() {
        deck.shuffle();                  // Shuffle the deck
        Blind();
        stake = 10;
        dealCards();                     // Method to deal cards to players
        blindBettingRound();

        addCommunityCard(3);
        bettingRound();

        addCommunityCard(1);
        bettingRound();

        addCommunityCard(1);
        bettingRound();

        dealer = playerList.get((playerList.indexOf(dealer)+1)% playerList.size());
    }
    // Method to get money from bigBlind and smallBlind
    private void Blind() {
        int smallBlindIndex = (playerList.indexOf(dealer) + 1) % playerList.size();
        int bigBlindIndex = (playerList.indexOf(dealer) + 2) % playerList.size();
        Player smallBlindPlayer = playerList.get(smallBlindIndex);
        Player bigBlindPlayer = playerList.get(bigBlindIndex);
        smallBlindPlayer.bet(bigBlind/2);
        bigBlindPlayer.bet(bigBlind);
    }

    // Method to deal cards to players
    private void dealCards() {
        int cardsPerPlayer = 2; // each player gets 2 cards
        for (Player player : playerList) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                Card drawnCard = deck.draw(); // Draw a card from the deck
                player.addCard(drawnCard);     // Add it to the player's hand
            }
        }
    }

    // Method to conduct a betting round
    private void blindBettingRound() {
        // Start from the player after the big blind
        int bigBlindIndex = (playerList.indexOf(dealer) + 2) % playerList.size();
        int startingIndex = (bigBlindIndex + 1) % playerList.size(); // Player after big blind

        for (int i = 0; i < playerList.size(); i++) {
            int currentIndex = (startingIndex + i) % playerList.size();
            Player currentPlayer = playerList.get(currentIndex);

            System.out.println(currentPlayer.getName() + ", it's your turn to act.");
            // Handle player action (bet, call, fold), replace with actual input handling
            ClientHandler clientHandler = playerClientMap.get(currentPlayer);
            String action = null;


            while (action != "check" || action != "bet" || action != "call" || action != "fold") {
                if(stage)
                clientHandler.getPlayerAction(currentPlayer.getHand());// Assume this method gets the action from the player
            }
            handlePlayerAction(currentPlayer, action); // Implement the logic for player actions
        }
    }

    // Method to add community cards (example for a game like Texas Hold'em)
    public void addCommunityCard(int NumberOfCard) {
        for(int i=0 ; i<NumberOfCard ; i++){
            communityCards.add(deck.draw());
        }
    }

    private void bettingRound() {
        // Start from the dealer
        int startingIndex = playerList.indexOf(dealer);

        for (int i = 0; i < playerList.size(); i++) {
            int currentIndex = (startingIndex + i) % playerList.size();
            Player currentPlayer = playerList.get(currentIndex);

            if(currentPlayer.hasFolded() || currentPlayer.isHasAllIn()){
                skip();
            }
            else{
                System.out.println(currentPlayer.getName() + ", it's your turn to act.");
                // Handle player action (bet, call, fold), replace with actual input handling
                ClientHandler clientHandler = playerClientMap.get(currentPlayer);
                String action = null;
                while (!currentPlayer.getAvailableAction(stake).contains(action)) {
                    action = clientHandler.getPlayerAction(currentPlayer.getHand()); // Get the action from the player
                }
                handlePlayerAction(currentPlayer, action); // Implement the logic for player actions
            }
        }
    }

    private void notification(){

    }

    // Assume this method handles the player's action
    private void handlePlayerAction(Player player, String action) {
        // Implement the action logic (e.g., bet, call, fold)
        switch (action.toLowerCase()) {
            case "check":
                // Handle betting logic
            case "raise":
                // Handle betting logic
                break;
            case "call":
                // Handle calling logic
                break;
            case "fold":
                // Handle folding logic

                break;
            default:
                System.out.println("Invalid action.");
                break;
        }
    }



    // Additional methods for game logic (betting, evaluating hands, etc.) can be added here
}