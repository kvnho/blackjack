import java.util.*;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void newDeck(){
        for(int i = 0; i < 4; i++){
            for(Rank cardRank : Rank.values()){
                this.cards.add(new Card(cardRank));
            }
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void removeCard(){
        this.cards.remove(0);
    }

    public Card getCard(){
        return this.cards.get(0);
    }

    public void draw(Deck deck){
        this.cards.add(deck.getCard());
        deck.removeCard();
    }

    public int handValue(){
        int value = 0;
        int aces = 0;

        for(Card card : this.cards){
            switch(card.getValue()){
                case TWO: value = value + 2; break;
                case THREE: value = value + 3; break;
                case FOUR: value = value + 4; break;
                case FIVE: value = value + 5; break;
                case SIX: value = value + 6; break;
                case SEVEN: value = value + 7; break;
                case EIGHT: value = value + 8; break;
                case NINE: value = value + 9; break;
                case TEN: value = value + 10; break;
                case JACK: value = value + 10; break;
                case QUEEN: value = value + 10; break;
                case KING: value = value + 10; break;
                case ACE: aces = aces + 1; break;
            }
        }

        for(int i = 0; i < aces; i++){
            if(value > 10){
                value = value + 1;
            }
            else{
                value = value + 11;
            }
        }

        return value;
    }

    public String toString(){
        String hand = "";
        for(Card card : this.cards){
            hand = hand + "[" + card.toString() + "]";
        }
        return hand;
    }

}
