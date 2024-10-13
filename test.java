import java.util.ArrayList;

public class test {

    public static void main(String args[]){

        Deck deck = new Deck();
        Player player = new Player("wilson",1000);
        player.addCard(deck.draw());
        player.addCard(deck.draw());
        player.addCard(deck.draw());
        player.showHand();


    }
}
