/*
 *  This constructs the players (including the user)
 */


 import java.util.*;

 public class Player {
 
    public int cash = 100;
 
    // This is the hand of cards that will be visible to the player
    public ArrayList<Card> hand = new ArrayList<>();

    public PlayerHand checkHand;
 
    // Only one dealer per game, will default to false for simplicity sake
    public boolean isDealer = false;
 
    // Same as their player number
    public int playerNum;

    // Flags for checking hand status

    /*
     *  The first thing that will be checked in the hand will be the flush,
     *  as it is the easiest check to make and also will be the first to 
     *  elimanate future checks for pairs (as if you have a flush hands that 
     *  require a minimum of pairs, will either not be possible because 
     *      1.  3 of a kind, fullhouse, as you cannot get these if you have 
     *          a flush.)
     * 
     *      2. The hands will be inferior to the your flush.
     * 
     *  Second will be the straight as (it is a little harder to check for
     *  is also a major component to the 2 highest conditions, and the 
     *  same logic of pair based hands that applied for flushes also applies
     *  for straights)
     * 
     *  Third will be the pair based hands as they are the hardest to check for
     *  and make up a majority of the winning hands. and it has the most in depth
     *  checkign system
     * 
     *  All the these will be checked for each player every time a table card is 
     *  flipped. If either the flush or the straight is checked for the player 
     *  (except for table straights/flushes as every player has acess to them)
     *  it will not check for pairs, 
     *  
     * 
     */
    public boolean checkForFlush = true;
    public boolean checkForStraight = true;


 
    public Player(int playerNum, boolean isDealer){
        this.playerNum = playerNum;
        this.isDealer = isDealer;
    }

    /*
     *          Information Retrieval Methods
     */

    public int getCashNum(){
        return cash;
    }
    public ArrayList<Card> getCards(){
        return hand;
    }
    public ArrayList<Card> getCheckHand(){
        return checkHand.getCheckCardList();
    }
    public boolean getDealerStatus(){
        return isDealer;
    }
    public int getPlayerNum(){
        return playerNum;
    }
    public int getHandLength(){
        return hand.size();
    }
    /*
     *          Changer methods
     */


    public void changeDealerStatus(){
        isDealer = !isDealer;
    }
    public void getWinnings(int pot){
        cash += pot;
    }

    /*
     *          Game Methods 
     */

    public int bet(int betNum){
        cash -= betNum;
        return betNum;
    }
    public void dealCards(Card card){
        hand.add(card);
    }
    // Called after all the cards are dealt
    public void createHand(){
        checkHand = new PlayerHand(hand);
    }
    public void addFlopCards(ArrayList<Card> flopCards){
        checkHand.addFlopCards(flopCards);
    }
    public void addTurnCard(Card turnCard){
        checkHand.addTurnCard(turnCard);
    }
    public void addRiverCard(Card riverCard){
        checkHand.addRiverCard(riverCard);
    }
    public void clearHand(){
        hand.clear();
        checkHand.clearHand();
    }
    
 
}
 