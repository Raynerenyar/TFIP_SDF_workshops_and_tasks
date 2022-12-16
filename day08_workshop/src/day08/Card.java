package day08;

public class Card {
    private final String suit;
    private final Integer value;
    private final String name;

    public Card(String suit, Integer value, String name) {
        this.suit = suit;
        this.value = value;
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    // @Override
    public String toString() {
        return "Card [suit=" + suit + ", value=" + value + ", name=" + name + "]";
    }

    // @Override
    // public String toString() {
    // return "Suit: %s, Name: %s".formatted(suit, name);
    // }

}
