/*
 *  Stores all 52 cards (not including Jokers)
 *  
 */

import java.util.*;

public class Deck {

    public ArrayList<Card> finalDeck = new ArrayList<>();
    public ArrayList<Card> playDeck = new ArrayList<>();

    // TODO Create a more effective storage system for cards that does not need to recreate the deck
    public Deck(){
        for(int suitNum = 1; suitNum < 5; suitNum++){
            for(int cardNum = 2; cardNum < 15; cardNum++){
                finalDeck.add(new Card(suitNum, cardNum));
            }
        }
        playDeck = finalDeck;
    }

    /*
     *          Retreival Methods
     */
    public int getCardsLeftInDeck(){
        return playDeck.size();
    }
    
    // Used to handle dealing, flop, turn, and river cards
    public Card getRandomCard(int cardIndex){

        Card card = playDeck.get(cardIndex);
        playDeck.remove(cardIndex);

        return card;
    }

    public void printDeck(){
        for(Card card : playDeck){
            System.out.println(card.getCardString());
        }
    }
}
