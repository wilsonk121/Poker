import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int money;
    private int betSize;
    private List<Card> hand; // List to store the player's cards
    private boolean hasFolded; // Track if the player has folded
    private boolean hasAllIn;

    // Constructor to initialize name and money
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.hand = new ArrayList<>(); // Initialize the hand
        this.hasFolded = false;
        this.hasAllIn = false;
        this.betSize = 0;
    }

    // Method to add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card); // Add card to the hand
    }

    // Method to place a bet
    public void bet(int amount) {
        // change to bool later
        if (amount <= money) {
            money -= amount; // Deduct the bet amount from the player's money
            betSize = amount;
            System.out.println(name + " bets " + amount);
        } else {
            System.out.println(name + " does not have enough money to bet that amount.");
        }
    }

    // Method to reset the player's hand
    public void resetHand() {
        hand.clear(); // Clear the cards in the player's hand
    }

    // Method to win money
    public void win(int amount) {
        money += amount; // Add winnings to the player's money
        System.out.println(name + " wins " + amount);
    }

    // Getter for the player's name
    public String getName() {
        return name;
    }

    // Getter for the player's money
    public int getMoney() {
        return money;
    }

    // Method to get the player's hand
    public List<Card> getHand() {
        return hand;
    }

    // Method to fold
    public void fold() {
        hasFolded = true;
        System.out.println(name + " has folded.");
    }

    // Method to check if the player has folded
    public boolean hasFolded() {
        return hasFolded;
    }

    // Method to display the player's hand
    public void showHand() {
        System.out.println(name + "'s hand: " + hand);
    }

    public void setBetSize(int betSize) {
        this.betSize = betSize;
    }

    public int getBetSize() {
        return betSize;
    }

    public List<String> getAvailableAction(int stake) {
        List<String> availableAction;
        if (betSize == stake){
           availableAction.add("check");
           if(money >= 5) {//small blind
               availableAction.add("raise");
           }
        } else if (betSize < stake) {
            if(money > (stake-betSize)){
                availableAction.add("call");
                availableAction.add("fold");
            }

        }
        return availableAction;
    }

    public boolean isHasAllIn(){
        return hasAllIn;
    }

    public void check(){

    }
    public void raise(int amount){
        money -= amount;
        if(money == 0) {
            hasAllIn = true;
        }
    }
    public void call(int stake){
        money = money - (stake-betSize);

    }


    public List<Integer> getMaxRaiseRange(int stake, int smallBlindSize) {

        List<Integer> range;
        int min;
        int max;
        min = stake-betSize;
        max = money;
        //no need..., min=call size, max=money, divisible by small blindSize


    }

    @Override
    public String toString() {
        return name + " (Money: " + money + ")";
    }
}