/*
 *  Class for the individual card 
 */

public class Card{

    // Suit will be ordered by num in which the tie breaker will be determined. 13 cards per suit
    public int suit;

    // Face cards will be ordered by num. Eg. Ace will be 1, Jack will be 11
    public int cardNum;

    public Card(int suit, int cardNum){
        this.suit = suit;
        this.cardNum = cardNum;
    }

    public int getSuit(){
        return this.suit;
    }

    public int getCardNum(){
        return this.cardNum;
    }

    //      This will deisplay what the card is in a string
    public String getCardString(){

        String cardString = "";

        // Determining the card value
        switch (cardNum){   
            case 2:
                cardString += "Two of ";
                break;
            case 3:
                cardString += "Three of ";
                break;
            case 4:
                cardString += "Four of ";
                break;
            case 5:
                cardString += "Five of ";
                break;
            case 6:
                cardString += "Six of ";
                break;
            case 7:
                cardString += "Seven of ";
                break;
            case 8:
                cardString += "Eight of ";
                break;
            case 9:
                cardString += "Nine of ";
                break;
            case 10:
                cardString += "Ten of ";
                break;
            case 11:
                cardString += "Jack of ";
                break;
            case 12:
                cardString += "Queen of ";
                break;
            case 13:
                cardString += "King of ";
                break;
            case 14:
                cardString += "Ace of ";
                break; 
        }

        // Determining the suit
        switch (suit){
            case 1:
                cardString += "Clubs";
                break;
            case 2:
                cardString += "Diamonds";
                break;
            case 3:
                cardString += "Hearts";
                break;
            case 4:
                cardString += "Spades";
                break;
        }

        return cardString;

    }

}
