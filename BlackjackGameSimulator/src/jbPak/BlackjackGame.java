package jbPak;

import java.util.Scanner;

/* 
 * This class simulates a blackjack game controlling the game loop while implementing the rules of the game
 * @author Sean Todd
 * @version 1.0
 */
public class BlackjackGame
{
   private Deck deck;
   private Player player;
   private Dealer dealer;
   private int bet;

   /*
    * Constructor that instantiates the deck, player and dealer and also shuffles
    * the deck
    * 
    * @param initialBalance the int value that contains the starting amount of
    * money for a player
    */
   public BlackjackGame(int initialBalance)
   {
      deck = new Deck();
      deck.shuffle();
      player = new Player(initialBalance, deck);
      dealer = new Dealer(deck);
   } // end constructor

   public static void main(String[] args)
   {
      // set constant value to start player with
      final int INITIAL_BALANCE = 100;

      BlackjackGame playBlackjack = new BlackjackGame(INITIAL_BALANCE);

      // play the game
      playBlackjack.startGame();
   }

   /*
    * This method is the game loop, implementing rules of black jack
    */
   public void startGame()
   {
      Scanner input = new Scanner(System.in);

      System.out.println(
            "\nJoin us at the blackjack table and let the game begin!\n");

      while (true)
      {
         System.out.println("Your balance: $" + player.getBalance());
         System.out.println();

         // Place a bet
         bet = getBet(input);
         if (bet == 0)
         {
            break; // Player decided to quit
         }

         // Deal initial cards
         dealInitialCards(player, dealer, deck);
         System.out.println();

         // determine if player is the winner in the first draw
         if ((player.getValueFromHand() == 21)
               || (dealer.getValueFromHand() == 21))
         {
            // if player has 21 and not dealer
            if (player.getValueFromHand() > dealer.getValueFromHand())
            {
               player.winBet(bet);
               System.out.println("Blackjack! Player wins!!");
            }
            // if dealer has 21 and not player
            else if (player.getValueFromHand() < dealer.getValueFromHand())
            {
               player.loseBet(bet);
               System.out.println(
                     "Blackjack! Dealer reveals cards " + dealer.getHand());
               System.out.println("Dealer wins!");
            }
            // if tie
            else
            {
               System.out.println("\nIt's a tie.\n");
               player.returnBet(bet);
            }
         }
         // if the values are not 21 after the initial deal then continue with the
         else if (!(player.getValueFromHand() == 21)
               || !(dealer.getValueFromHand() == 21))
         {
            // Player's turn
            while (true)
            {
               // gameStatus();
               if (!player.takeTurn(input))
               {
                  break;
               }
               System.out.println();
            }

            // Dealer's turn
            dealer.dealerTakesTurn();

            // Determine the winner and update player's balance
            determineWinner();

            // Ask the player if they want to play again
            if (!playAgain(input))
            {
               break;
            }
         }

         // Reset hands and deck for a new round
         player.resetHand();
         dealer.resetHand();
         deck.reset();
      }

      System.out.println(
            "\nClosing out the cards – thanks a bunch for playing the blackjack game!\n");
   } // end startGame()

   /*
    * This method deals initial two cards to the player and dealer and prints out
    * the hand of the user and one card of the dealer
    * 
    * @param player the player object
    * 
    * @param dealer the dealer object
    * 
    * @param deck the deck object
    */
   private static void dealInitialCards(Player player, Dealer dealer,
         Deck deck)
   {
      player.hit();
      player.hit();
      dealer.hit();
      dealer.hit();
      System.out.println("In your hand: " + player.getHand());
      System.out.println("Dealer's hand: " + dealer.getHand().get(0)
            + " and an unknown card");
   }

   /*
    * This method gets the bet from the user
    * 
    * @param scanner the int input from user
    * 
    * @return returns int bet value
    */
   private int getBet(Scanner scanner)
   {
      int balance = player.getBalance();
      System.out
            .print("Place your bet (0 to quit, balance: $" + balance + "): ");
      while (true)
      {
         if (scanner.hasNextInt())
         {
            int bet = scanner.nextInt();
            if (bet >= 0 && bet <= balance)
            {
               return bet;
            } else
            {
               System.out.println(
                     "Invalid bet amount. Please enter a valid bet.");
            }
         } else
         {
            System.out.println("Invalid input. Please enter a valid bet.");
            scanner.next(); // Clear invalid input
         }
      }
   } // end getBalance()

   /*
    * This method prints the player's current balance and hand and the dealers hand
    */
   private void gameStatus()
   {
      System.out.println("Your balance: $" + player.getBalance());
      System.out.println("Your hand: " + player.getHand());
      System.out.println("Dealer's hand: " + dealer.getHand());
   } // end gameStatus()

   /*
    * This method determines if the player or dealer is the winner of the round
    */
   private void determineWinner()
   {
      int playerPointValue = player.getValueFromHand();
      int dealerPointValue = dealer.getValueFromHand();

      if (playerPointValue > 21)
      {
         System.out.println("\nPlayer busts. Dealer wins!\n");
         player.loseBet(bet);
      } else if (dealerPointValue > 21 || playerPointValue > dealerPointValue)
      {
         System.out.println("\nPlayer wins!!\n");
         player.winBet(bet);
      } else if (playerPointValue == dealerPointValue)
      {
         System.out.println("\nIt's a tie.\n");
         player.returnBet(bet);
      } else
      {
         System.out.println("\nDealer wins!\n");
         player.loseBet(bet);
      }
      System.out
            .println("Your current balance is $" + player.getBalance() + "\n");
   } // end determineWinner()

   /*
    * This method returns a boolean to indicate player's choice to continue playing
    * 
    * @param scanner user input
    * 
    * @return response in a boolean value true or false
    */
   private boolean playAgain(Scanner scanner)
   {
      if (player.getBalance() == 0)
      {
         System.out.println(
               "You have no money to bet and can no longer play, please leave the table");
         return false;
      } else
      {
         System.out.print("Play again? (y/n): ");
         String response = scanner.next().trim().toLowerCase();
         return response.equals("y");
      }
   } // end playAgain()

} // end BlackjackGame
