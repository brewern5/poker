import java.util.ArrayList;
import java.util.InputMismatchException;
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

            for (Player player : currentPlayers) {
                //deal two cards
                player.dealCards(game.dealCards());
                player.dealCards(game.dealCards());

                player.createHand();



                System.out.println("\nPlayer: " + player.getPlayerNum() +" cards: " + player.getCards().get(0).getCardString() + "    " + player.getCards().get(1).getCardString());
            }

            //  Tells what card the user has
            System.out.println("\nYou have:  ");

            for(Card card : userPlayer.getCards()){
                String cardString = card.getCardString();
                System.out.print("     " + cardString + " ");
            }

            System.out.println("\n");

            //game.gameDeck.printDeck();


            // TODO First Round Betting
            System.out.println("Begin First Round Betting!");
            boolean firstRoundBetting = true;
            while(firstRoundBetting){
                System.out.print("    Place your bet: ");
                try{
                    int playerBet = in.nextInt();
                    in.nextLine();
                    firstRoundBetting = false;
                }catch(InputMismatchException e){
                    System.out.println("\n \\\u001B[1m" + "You need to enter a number!" + "\\\u001B[0m \n");
                    in.nextLine();
                }
            }
            System.out.println();


            /*
             *          THE FLOP
             */
            ArrayList<Card> flopCards = game.theFlop();
            System.out.println("The flop cards are:  ");
            for(Card card : flopCards){
                System.out.print("     " + card.getCardString() + "  ");
            }
            System.out.println("\n");

            //game.gameDeck.printDeck();

            // Adds the flop cards to a hidden player deck to check the cards early on
            for(Player player : currentPlayers){
                player.addFlopCards(flopCards);
                player.checkCards();
            }
            /*
             *          End Flop
             */

            
            // TODO Second Round Betting
            System.out.println("Begin Second Round Betting!");
            boolean secondRoundBetting = true;
            while(secondRoundBetting){
                System.out.print("    Place your bet: ");
                try{
                    int playerBet = in.nextInt();
                    in.nextLine();
                    secondRoundBetting = false;
                }catch(InputMismatchException e){
                    System.out.println("\n \\\u001B[1m" + "You need to enter a number!" + "\\\u001B[0m \n");
                    in.nextLine();
                }
            }
            System.out.println();
            
            /*
             *          The Turn
             */

            // reveal the Turn card
            Card turn = game.turn();
            System.out.println("The turn card is: ");
            System.out.println("    " + turn.getCardString());
            for (Player player : currentPlayers){
                player.addTurnCard(turn);
                player.checkCards();
            }


            System.out.println("Table Cards");
            for(Card card : flopCards){
                System.out.print(" "+card.getCardString() + "   ");
            }
            System.out.print(" "+turn.getCardString() + "   ");
            System.out.println("\n");

            //game.gameDeck.printDeck();

            /*
             *          End the Turn
             */

            // TODO Third Round Betting
            System.out.println("Begin Third Round Betting!");
            boolean thirdRoundBetting = true;
            while(thirdRoundBetting){
                System.out.print("    Place your bet: ");
                try{
                    int playerBet = in.nextInt();
                    in.nextLine();
                    thirdRoundBetting = false;
                }catch(InputMismatchException e){
                    System.out.println("\n \\\u001B[1m" + "You need to enter a number!" + "\\\u001B[0m \n");
                    in.nextLine();
                }
            }
            System.out.println();

            /*
             *          The River
             */

            // Reveal the River card
            Card river = game.river();
            System.out.println("The river card is: ");
            System.out.println("    " + river.getCardString());
            for (Player player : currentPlayers){
                player.addRiverCard(river);
                player.checkCards();
            }

            System.out.println("Table Cards");
            for(Card card : flopCards){
                System.out.print(" "+card.getCardString() + "   ");
            }
            System.out.print(" "+turn.getCardString() + "   ");
            System.out.print(" "+river.getCardString() + "   ");
            System.out.println("\n");

            //game.gameDeck.printDeck();

            /*
             *          End the River
             */

            // TODO Fourth Round Betting
            System.out.println("Begin the Last Round of Betting!");
            boolean finalRoundBetting = true;
            while(finalRoundBetting){
                System.out.print("    Place your bet: ");
                try{
                    int playerBet = in.nextInt();
                    in.nextLine();
                    finalRoundBetting = false;
                }catch(InputMismatchException e){
                    System.out.println("\n \\\u001B[1m" + "You need to enter a number!" + "\\\u001B[0m \n");
                    in.nextLine();
                }
            }
            System.out.println();

            // TODO the Draw (show all player hands) determine winner and delegate winnings

            // Want to play again
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
                    // TODO change dealer
                    game.reshuffle();

                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                    System.out.println("***************************************************************************");
                    System.out.println("*                                                                         *");
                    System.out.println("*                                                                         *");
                    System.out.println("*                        NEW         GAME                                 *");
                    System.out.println("*                                                                         *");
                    System.out.println("*                                                                         *");
                    System.out.println("***************************************************************************");


                    stillThinking = false;  
                }
                else{
                    System.out.println("Invalid Character!");
                }
            }

        }
    }

}
