import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private int size;

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
        this.size = deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void shuffle(){
        for (int i = 0; i < size; i++){
            int j = (int)(Math.random() * size);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

}
