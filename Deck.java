import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cardList;

    public Deck() {
        cardList = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cardList.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public Card draw() {
        return cardList.isEmpty() ? null : cardList.remove(cardList.size() - 1);
    }

    public List<Card> getCards() {
        return cardList;
    }
}