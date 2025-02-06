import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        boolean gameIsOn = true;

        int numOfPlayers = 3;
        //creates the game
        pokerGame game = new pokerGame(numOfPlayers, 100);

        // Retrieve players (Maybe keep only the current players as they will be stored elsewhere)
        ArrayList<Player> currentPlayers = game.getTotalPlayers();

        System.out.println("Number of players: " + currentPlayers.size());

        //      The user Player is "player 0"
        Player userPlayer = currentPlayers.get(0);

        // Game starts and players are dealt cards
        while(gameIsOn){

            // TODO Add a card handler to determine and show the actual card values

            // TODO Deal cards
            for (Player player : currentPlayers) {
                //deal two cards
                player.dealCards(game.dealCards());
                player.dealCards(game.dealCards());

                player.createHand();

                //System.out.println("Player: " + player.getPlayerNum() +"\n Hand size: " + player.getHandLength());
            }

            //  Tells what card the user has
            System.out.println("You have:  ");

            for(Card card : userPlayer.getCards()){
                String cardString = card.getCardString();
                System.out.println("     " + cardString + " ");
            }


            // TODO First Round Betting
            System.out.println("Begin First Round Betting!");

            /*
             *          THE FLOP
             */
            ArrayList<Card> flopCards = game.theFlop();

            // Adds the flop cards to a hidden player deck to check the cards early on
            for(Player player : currentPlayers){
                player.addFlopCards(flopCards);
            }

            /*
             *          End Flop
             */

            // TODO Second Round Betting
            System.out.println("Begin Second Round Betting!");

            /*
             *          The Turn
             */

            // reveal the Turn card
            Card turn = game.turn();
            for (Player player : currentPlayers){
                player.addTurnCard(turn);
            }

            /*
             *          End the Turn
             */

            // TODO Third Round Betting
            System.out.println("Begin Third Round Betting!");

            /*
             *          The River
             */

            // Reveal the River card
            Card river = game.turn();
            for (Player player : currentPlayers){
                player.addRiverCard(river);
            }

            /*
             *          End the River
             */

            // TODO Fourth Round Betting
            System.out.println("Begin the Last Round of Betting!");

            // TODO the Draw (show all player hands)

            // TODO determine winner and delegate winnings

            // TODO reset game (ask player to player again?)
            boolean stillThinking = true;

            while(stillThinking){

                currentPlayers = game.getTotalPlayers();

                // Resets player hands and will see if players can still play by checking their cash
                for(Player player : currentPlayers){
                    if(player.getCashNum() < 1){
                        System.out.println(player.getPlayerNum() + " Has busted out!");
                        game.bust(player.getPlayerNum());
                        currentPlayers.remove(player.getPlayerNum());
                    }
                    player.clearHand();
                }

                System.out.println("Keep Playing? (Y/N)");

                String keepPlaying = in.nextLine();

                if(keepPlaying.equals("n") || keepPlaying.equals("N")){
                    in.close();
                    stillThinking = false;
                    gameIsOn = false;
                }
                else if(keepPlaying.equals("y") || keepPlaying.equals("Y")){
                    // TODO reshuffle the deck and change dealer



                    stillThinking = false;  
                }
                else{
                    System.out.println("Invalid Character!");
                }
            }

        }
    }

}
