/*
 *      Holds all the cards and functions for each players hands
 *      
 * 
 */

 import java.util.ArrayList;


public class PlayerHand {

    public ArrayList<Card> playerHand;

    // These are the cards that are dealt to the player
    public Card firstPlayerCard;
    public Card secondPlayerCard;

    public Card firstFlopCard;
    public Card secondFlopCard;
    public Card thirdFlopCard;

    public Card turnCard;

    public Card riverCard;

    /*
     * 
     *          Basic constructor methods
     *      (I know all of these aren't called 
     *       with the inital constructor method)
     * 
     */

    public PlayerHand(ArrayList<Card> playerHand){
        this.playerHand = playerHand;
    }

    public void addFlopCards(ArrayList<Card> flopCards){
        for(Card flopCard : flopCards){
            playerHand.add(flopCard);
        }
        firstFlopCard = playerHand.get(2);
        secondFlopCard = playerHand.get(3);
        thirdFlopCard = playerHand.get(4);
    }

    public void addTurnCard(Card turnCard){
        playerHand.add(turnCard);
        this.turnCard = turnCard;
    }

    public void addRiverCard(Card riverCard){
        playerHand.add(riverCard);
        this.riverCard = riverCard;
    }

    /*
     *          End Constructor Methods
     */


    /* 
     *          Retrieval Methods
     */
    public ArrayList<Card> getCheckCardList(){
        return playerHand;
    }
    /*
     *          End Retrieval Methods
     */


    /*
     *          Editing methods
     */

    public void clearHand(){
        playerHand.clear();
    }


}
