package day08;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("This is a deck of playing cards");

        Deck deck = new Deck();
        System.out.println("==========Cards in deck contains==========");
        for (Card card : deck.getDeck()) {
            System.out.printf("no. %d, %s, %s\n", deck.getDeck().indexOf(card), card.getSuit(), card.getName());
        }
        System.out.println("shuffle deck");
        deck.shuffleDeck();
        List<Card> test = deck.getDeck();
        for (int i = 0; i < test.size(); i++) {
            // System.out.printf("%d:Suit: %s, Name: %s\n", i, test.get(i).getSuit(),
            // test.get(i).getName());
            System.out.println(test.get(i).toString());
        }

        System.out.println("Draw a card");
        Card drawnCard = deck.drawCard();
        System.out.printf("Suit: %s, Value: %d\n", drawnCard.getSuit(), drawnCard.getValue());
    }
}
