public class Card {
    private String suit; // e.g., "Hearts", "Diamonds"
    private String rank; // e.g., "2", "3", "King"

    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Card(String suit, String rank) {
        if (!isValidSuit(suit) || !isValidRank(rank)) {
            throw new IllegalArgumentException("Invalid suit or rank");
        }
        this.suit = suit;
        this.rank = rank;
    }

    private boolean isValidSuit(String suit) {
        for (String validSuit : SUITS) {
            if (validSuit.equals(suit)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidRank(String rank) {
        for (String validRank : RANKS) {
            if (validRank.equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}