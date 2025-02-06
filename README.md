# Poker Game

## Brief Description of Texas Hold'em

Texas Hold'em is a popular variant of poker. Each player is dealt two private cards, and five community cards are dealt face-up on the "board". Players aim to make the best five-card hand using any combination of their two private cards and the five community cards. The game consists of several betting rounds: pre-flop, flop, turn, and river. The player with the best hand (or the last player remaining after all others have folded) wins the pot.

## Purpose of This Project

The motivation behind creating this poker game was to combine my enjoyment of the game with a challenge to apply and enhance my Object-Oriented Programming (OOP) skills acquired during my college education. This project was designed and implemented solely based on my understanding of the game, without external resources. The focus was on optimizing the code and ensuring an entertaining experience.

## Project Structure

### pokerGame

The `pokerGame` class manages the overall game logic, including dealing cards, managing players, and handling the different stages of the game.

### Card

The `Card` class represents an individual playing card with a suit and a number. It includes methods to retrieve the card's suit, number, and a string representation of the card.

### Deck

The `Deck` class represents a standard deck of 52 playing cards. It includes methods to shuffle the deck, deal cards, and keep track of the remaining cards in the deck.

### Player

The `Player` class represents a player in the game. It includes attributes for the player's cash, hand, and dealer status. Methods in this class handle betting, dealing cards, and managing the player's hand.

### PlayerHand

The `PlayerHand` class represents a player's hand, including the initial two cards and the community cards (flop, turn, and river). It includes methods to add cards to the hand and retrieve the current hand for evaluation.

## How to Run

1. Compile the Java files:
   ```sh
   javac *.java
