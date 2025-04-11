/*
 *  This is where the inital game will be set up. It will keep track of players, for example if one player 
 *  doesn't have any money they will be removed through here.
 */

import java.util.*;

public class pokerGame {

    public int numOfPlayers;

    // What the first round min bet is (Overrided for the little blind)
    public int buyIn;

    Random random = new Random();

    // Holds all the game players, so if a player folds they are remembered
    public ArrayList<Player> totalPlayers = new ArrayList<>();

    /*
     *        |  Variables that will ABSOLUTLY change |
     *        V                                       V
     */

    // Holds the active players in the game e.g. the players who are playing the hand and have not folded
    public ArrayList<Player> currentHandPlayers = new ArrayList<>();

    public int playerThatIsDealer = 0;

    public Deck gameDeck;


    /*
     *          Instantiation Methods
     */

    public pokerGame(int numOfPlayers, int buyIn){
        this.numOfPlayers = numOfPlayers;
        this.buyIn = buyIn;

        // Create players
        for(int i = 0; i < numOfPlayers; i++){
            totalPlayers.add(createPlayer((i)));
            currentHandPlayers.add(totalPlayers.get(i));
        }
        
        //Create the current game deck
        gameDeck = new Deck();

    }

    // Creates players (including the user) based on how many players the user wants
    public Player createPlayer(int playerNum){
        
        Player player;
    
        if(playerNum == 0){
            player = new Player(playerNum, true);
        }
        else{
            player = new Player(playerNum, false);
        }
        return player;
    }

    /*
     *          Information retreival Methods
     */

    public int getNumOfPlayers(){
        return numOfPlayers;
    }

    public int getBuyIn(){
        return buyIn;
    }

    public int getPlayerThatIsDealer(){
        return playerThatIsDealer;
    }

    public ArrayList<Player> getTotalPlayers(){
        return totalPlayers;
    }

    public ArrayList<Player> getCurrentPlayers(){
        return currentHandPlayers;
    }

    /*
     *          Absoulte game methods (Methods that will always be called)
     */
    public Card dealCards(){
        //System.out.println("Cards left in the deck: " + gameDeck.getCardsLeftInDeck());
        return gameDeck.getRandomCard(random.nextInt(gameDeck.getCardsLeftInDeck()));
    }

    public ArrayList<Card> theFlop(){

        ArrayList<Card> flopCards = new ArrayList<Card>();

        for(int i = 0; i < 3; i++){
            flopCards.add(gameDeck.getRandomCard((random.nextInt(gameDeck.getCardsLeftInDeck()))));
        }

        return flopCards;
    }

    public Card turn(){
        Card turn = gameDeck.getRandomCard(random.nextInt(gameDeck.getCardsLeftInDeck()));

        return turn;
    }

    public Card river(){
        Card river = gameDeck.getRandomCard(random.nextInt(gameDeck.getCardsLeftInDeck()));

        return river;
    }

    public void changeDealer(){
        if(playerThatIsDealer == (numOfPlayers-1)){
            playerThatIsDealer = 0;
        }
        else{
            playerThatIsDealer += 1;
        }

        totalPlayers.get(playerThatIsDealer).changeDealerStatus();
    }

    /*
     *          Potential game methods
     */

    //  Player folds their current hand and will play the next game
    public void fold(int playerNum){
        currentHandPlayers.remove(playerNum);
    }

    //  Player has ran out of money and will be out of the game
    public void bust(int playerNum){
        totalPlayers.remove(playerNum);
    }

    /*
     *          End-Game determination
     */

    public void reshuffle(){
        gameDeck = new Deck();
    }

    public void winningHand(){
        // TODO: Handle card values for a 'winning hand' 
        
    }


    /*
     * 
     *          DEBUG
     * 
     */

     public Card addDefiniteCard(int i){
        return gameDeck.getDefinateCards(i);
     }
}
