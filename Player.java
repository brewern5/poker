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
    
    public void checkCards(){
        checkHand.checkCards();
    }
 


}
 