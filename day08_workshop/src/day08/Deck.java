package day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final Integer TOTAL_NUM_OF_CARDS = 52;
    private final List<Card> DECK = new ArrayList<Card>(TOTAL_NUM_OF_CARDS);
    private final String[] SUITS = { "Diamond", "Club", "Heart", "Spade" };
    private final String[] CARD_NAME = { "Ace", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Jack", "Queen", "King" };
    private final Integer MAXVALUE = 13;

    public Deck() {
        for (String suit : SUITS) {
            for (int i = 0; i < MAXVALUE; i++) {
                Card card = new Card(suit, i + 1, CARD_NAME[i]);
                DECK.add(card);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(DECK);
    }

    public Card drawCard() {
        return DECK.remove(0);
    }

    public List<Card> getDeck() {
        return DECK;
    }
}
