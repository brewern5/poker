/*
 *      
 *          Holds all the cards and functions for each players hands
 * 
 */

 import java.util.ArrayList;


public class PlayerHand {

    public ArrayList<Card> playerHand;

    public ArrayList<Card> tableCards = new ArrayList<>();

    public ArrayList<Card> bestHand = new ArrayList<>();

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
     */
    

    public boolean hasStraightFlush = false;
    
    public boolean checkForFlush = true;
    public boolean hasFlush = false;

    public boolean checkForStraight = true;
    public boolean hasStraight = false;

    // These will turn false if a straight or flush is found, 
    public boolean checkForPairs = true;
    public boolean hasPair = false;

    // This will turn true if a pair is found then children will turn on too (four of a kind)
    public boolean checkForThree = false;
    public boolean hasThree = false;

    public boolean checkForFour = false;

    // This will be the highest card in the hand (Ace will be handled diffreently since ace can be high and low)
    public Card kicker;

    // Found in the check for flush Checker
    boolean playerCardsSameSuit = false;
    int numOfCardsOfSameSuit = 0;
    ArrayList<Card> flushTempArr = new ArrayList<>();


    /*
     * 
     *          Basic constructor methods
     *      (I know all of these aren't called 
     *       with the inital constructor method)
     * 
     */

    public PlayerHand(ArrayList<Card> playerHand){
        this.playerHand = playerHand;

        // After the hand is initalized the kicker will be determined and then will check for potential matches to other conditions

        //      Kicker/Pair determination
        if(playerHand.get(0).getCardNum() > playerHand.get(1).getCardNum()){
            kicker = playerHand.get(0);
        }
        else if(playerHand.get(0).getCardNum() < playerHand.get(1).getCardNum()){
            kicker = playerHand.get(1);
        }
        else if(playerHand.get(0).getCardNum() == playerHand.get(1).getCardNum()){
            kicker = playerHand.get(0);
            hasPair = true;
            bestHand.add(playerHand.get(0));
            bestHand.add(playerHand.get(1));
            checkForThree = true;
        }

    }

    public void addFlopCards(ArrayList<Card> flopCards){
        for(Card flopCard : flopCards){
            tableCards.add(flopCard);
        }
    }

    public void addTurnCard(Card turnCard){
        tableCards.add(turnCard);
    }

    public void addRiverCard(Card riverCard){
        tableCards.add(riverCard);
    }

    /*
     *          End Constructor Methods
     */


    /* 
     *          Retrieval Methods
     */
    public ArrayList<Card> getCheckCardList(){
        return tableCards;
    }
    /*
     *          End Retrieval Methods
     */


    /*
     *          Editing methods
     */

    public void clearHand(){
        playerHand.clear();

        tableCards.clear();

        bestHand.clear();
        // TODO: think about whether or not it should just instantiate a new 'PlayerHand'
    }

    public ArrayList<Card> sortCardsHighToLow(ArrayList<Card> list){

        // will need to keep the current size as the tempArr will store the cards sorted in order from highest to lowest
        int size = list.size();

        try{
            for(int i = 0; i <= size ; i++){

                Card highestCard = list.get(i);

                    // Sorting the cards from highest to lowest
                for(Card card : list){
                    if(highestCard.getCardNum() < card.getCardNum()){
                        if(highestCard.getCardNum() == card.getCardNum()){
                            list.remove(card);
                        }
                        highestCard = card;
                    }   
                }
                    
                // remove the card and move it to the back of the list
                list.add(highestCard);
                list.remove(highestCard);
            }
        }catch(IndexOutOfBoundsException e){
            
        }

        return list;
    }

    public boolean checkHandForFlush(){

        int lower = 0;

        if(numOfCardsOfSameSuit > 1){
            lower = 1;
        }
        
        // Checks the players 2 hand cards to see if they're the same first, if not then it'll check the hand cards individually
        if(!playerCardsSameSuit){
            if(playerHand.get(0).getSuit() == playerHand.get(1).getSuit()){
                numOfCardsOfSameSuit = 2;
                lower = 1;
                for(int i = 0; i < 2; i++){
                    flushTempArr.add(playerHand.get(i));
                }
                playerCardsSameSuit = true;
            }
        }
        // if the hand cards != eachother, and the table card size is 3, no flush is possible
        else if(tableCards.size() < 4){
            return false;
        }
        else{
            numOfCardsOfSameSuit = 2;
        }

        System.out.println("\nPlayerHand");
        for(Card card : playerHand){
            System.out.print(" "+card.getCardString() + "   ");
        }
        System.out.println("\n");

        // This will be the hand card that is getting checked, if they are both the same suit only one will get checked
        for(int handCard = lower; handCard < 2; handCard++){ 

            Card firstHandCard = playerHand.get(handCard);

            if(!playerCardsSameSuit){
                flushTempArr.add(firstHandCard);
                numOfCardsOfSameSuit++;
            }

            //for(int tableCardNum = 0; tableCardNum < playerHand.size()-2; tableCardNum++){

            for(int tableCardNum = 0; tableCardNum < tableCards.size(); tableCardNum++){

                // TODO : This may be where the Flush issue stems from
                Card tableCard = tableCards.get(tableCardNum);


                System.out.println("\n Table Card first check " + tableCard.getCardString() + "\n");

                //Card tableCard = playerHand.get(2 + tableCardNum);

                if(firstHandCard.getSuit() == tableCard.getSuit()){
                    if(firstHandCard.getCardNum() != tableCard.getCardNum()){
                        numOfCardsOfSameSuit++;
                        flushTempArr.add(tableCard);
                        System.out.println(tableCard.getCardString());
                    }
                }
            }

            System.out.println("\n Flush temp array mid check");
            for(Card card : flushTempArr){
                System.out.println(card.getCardString());
            }
            System.out.println();

            if(flushTempArr.size() < 5 || numOfCardsOfSameSuit < 5){
                
                if(playerCardsSameSuit){
                    numOfCardsOfSameSuit = 2;
                    flushTempArr.clear();
                    flushTempArr.add(playerHand.get(0));
                    flushTempArr.add(playerHand.get(1));
                }
                else{
                    numOfCardsOfSameSuit = 0;
                    flushTempArr.clear();
                }
                break;
            }
        }
        if(numOfCardsOfSameSuit > 4){

            System.out.println("Flush Temp Array if has 5 cards");
            for (Card card : flushTempArr) {
                System.out.println(card.getCardString());
            }

            flushTempArr = sortCardsHighToLow(flushTempArr);

            /* 
            System.out.println("Before sorting: ");
            for(Card card : flushTempArr){
                System.out.print(card.getCardString() + "   ");
            }
            System.out.println();
            */

            //TODO: check for str8 flush
            System.out.println("You have a flush!");
            
            if(flushTempArr.size() > 4){
                for(int i = 0; i < 5; i++){
                    bestHand.add(flushTempArr.get(i));
                    System.out.print(" " + flushTempArr.get(i).getCardString() + "  ");
                }
                System.out.println("\n");

                /*
                //System.out.println("\nAfter sorting: ");
                //for(Card card : bestHand){
                //    System.out.print(card.getCardString() + "   ");
                //}
                //System.out.println();
                */
            }

            // Does not need to check for pairs as the flush is superior
            checkForPairs = false;

            return true;
        }

        /*  Debug
        for(Card card : tempArr){
            System.out.println(card.getCardString());
        }
        */
        return false;
    }

    public boolean checkHandForStr8(){

        ArrayList<Card> tempArr = new ArrayList<>();

        tempArr = sortCardsHighToLow(tempArr);

        boolean hasBestStr8 = false;

        if(tempArr.size() > 5){

            // Goes backwards to check the last 5 (lowest) cards first because if the 3rd and 4th aren't in order, no str8 will be possible
            for(int highStr8Card = 2; highStr8Card > -1; highStr8Card--){
                int firstCard = highStr8Card;
                for(int i = 0; i < 4; i++){
                    if(tempArr.get(firstCard).getCardNum() != tempArr.get(firstCard + 1).getCardNum()){
                        if(highStr8Card == 2){
                            checkForStraight = false;
                            return false;
                        } 
                        break; 
                    }
                    else if(tempArr.get(firstCard).getCardNum() == tempArr.get(firstCard + 1).getCardNum()){
                        firstCard++;
                    }
                    System.out.println(i);
                }
            }

        }
        else if(tempArr.size() == 5){

        }
        
        if(hasBestStr8){ return true; }
        return false;
    }

    public void checkCards(){

        //tableCards = playerHand;

        if(checkForFlush && !hasFlush){
            hasFlush = checkHandForFlush();
        }   
        else if(checkForStraight && !hasStraight){
            hasStraight = checkHandForStr8();
        }

    }


    /*
     * 
     *          DEBUG
     * 
     */



     public void overWritePlayerHand(){
        playerHand.clear();
     }

     public void addToPlayerHand(Card card){
        playerHand.add(card);
     }


}
