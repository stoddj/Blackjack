package jbPak;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
 * This class simulates the player in the a game of blackjack and implements the PlayerBehavior interface
 * @author Sean Todd
 * @version 1.0
 */
public class Player implements PlayerBehavior
{
   // variables needed
   private int balance;
   private List<Card> hand;
   private Deck deck;

   /*
    * Constructor that instantiates the deck and hand of the player
    * 
    * @param initialBalance the int value that contains the starting amount of
    * money for a player
    * 
    * @param deck the Deck object that contains the list of cards used for the game
    */
   public Player(int initialBalance, Deck deck)
   {
      balance = initialBalance;
      hand = new ArrayList<>();
      this.deck = deck;
   } // end constructor

   /*
    * This method adds a card (hit) to the player's hand
    */
   @Override
   public void hit()
   {
      Card cardDrawn = deck.drawCard();
      if (cardDrawn != null)
      {
         hand.add(cardDrawn);
         System.out.println("You drew " + cardDrawn);
      } else
      {
         System.out.println("Empty deck.");
      }
   } // end hit()

   /*
    * Method ends the player's turn
    */
   @Override
   public void stay()
   {
      System.out.println("Turn ends. Now it's the dealer's turn.");
   } // end stay()

   /*
    * This method counts the accumulated points in the player's hand
    * 
    * @return valueInHand the int value of points in hand
    */
   @Override
   public int getValueFromHand()
   {
      int valueInHand = 0;
      int numOfAces = 0;

      for (int index = 0; index < hand.size(); index++)
      {
         Card card = hand.get(index);
         int cardValue = card.getPointValue();
         valueInHand = valueInHand + cardValue;

         if (card.getRank() == Card.Rank.ACE)
         {
            numOfAces++;
         }
      } // end for loop
        // if you have Aces change the value from 11 to 1 to prevent busting
      while ((numOfAces > 0) && (valueInHand > 21))
      {
         valueInHand = valueInHand - 10;
         numOfAces--;
      } // end while
      return valueInHand;
   } // end getValueFromHand

   /*
    * Method gets the cards in the player's hand
    * 
    * @return Array List of cards in player's hand
    */
   @Override
   public List<Card> getHand()
   {
      return new ArrayList<>(hand);
   }

   /*
    * This method clears the cards from the players's hand
    */
   @Override
   public void resetHand()
   {
      hand.clear();
   }

   /*
    * This method places the player bet
    * 
    * @param amount the int value of bet
    */
   public void placeBet(int amount)
   {
      // check to make sure the bet amount is between 0 and the current balance
      if ((amount >= 0) && (amount <= balance))
      {
         balance = balance - amount;
      } else
      {
         // check to see if bet is less than 0
         if (amount >= 0)
         {
            System.out.println("The amount entered is too low.");
         } else if (amount <= balance) // check to see if bet is greater than balance
         {
            System.out.println(
                  "The amount entered is more than you have to bet.");
         }
      }
   } // end placeBet ()

   /*
    * This method updates the balance after winning bet
    * 
    * @param bet the int value of bet
    */
   public void winBet(int bet)
   {
      balance = balance + bet;
   } // end winBet()

   /*
    * This method updates the balance after losing bet
    * 
    * @param bet the int value of bet
    */
   public void loseBet(int bet)
   {
      balance = balance - bet;
   } // end loseBet()

   /*
    * This method gets the current player balance
    * 
    * @return the int value of balance
    */
   public int getBalance()
   {
      return balance;
   } // end getBalancce()

   /*
    * This method updates the balance based on the returned bet, meaning no points
    * added or removed from balance
    * 
    * @param bet the int value of bet
    */
   public void returnBet(int bet)
   {
      bet = 0;
      // to update balance with 0 bet earned and return
      balance = balance + bet;
   } // end returnBet()

   /*
    * This method contains the logic to implement the player's turn
    * 
    * @param scanner input to either hit or stay
    * 
    * @return the boolean value to take turn or end turn
    */
   public boolean takeTurn(Scanner scanner)
   {
      System.out.print("Do you want to (h)it or (s)tay? ");
      String choice = scanner.next().trim().toLowerCase();

      if ("h".equals(choice))
      {
         hit();
         return getValueFromHand() <= 21;
      } else if ("s".equals(choice))
      {
         return false; // Player chooses to stay
      } else
      {
         System.out.println(
               "Invalid choice. Please enter 'h' to hit or 's' to stay.");
         return true; // Prompt the player again
      }
   } // end takeTurn()

} // end Player class
