package jbPak;

import java.util.ArrayList;
import java.util.List;

/* 
 * This class simulates the dealer in the a game of blackjack and implements the PlayerBehavior interface
 * @author Sean Todd
 * @version 1.0
 */
public class Dealer implements PlayerBehavior
{
   private List<Card> hand;
   private Deck deck;

   /*
    * Constructor that instantiates the deck, and hand for the dealer
    * 
    * @param deck the Deck object that contains the list of cards used for the game
    */
   public Dealer(Deck deck)
   {
      this.hand = new ArrayList<>();
      this.deck = deck;
   } // end constructor

   /*
    * This method has the logic to add more cards (hit) to the dealers hand until
    * the point value reaches 17
    */
   @Override
   public void hit()
   {
      int valueInHand = getValueFromHand();

      // Hit until while the hand is leass than 17
      while (valueInHand < 17)
      {
         Card drawnCard = deck.drawCard();
         hand.add(drawnCard);
         valueInHand = getValueFromHand(); // Update the value of points in hand
      }
   } // end hit()

   /*
    * Method ends the dealer's turn
    */
   @Override
   public void stay()
   {
      System.out.println("Dealer ends turn");
   }

   /*
    * This method counts the accumulated points in the dealer's hand
    * 
    * @return valueInHand the int value of points in hand
    */
   @Override
   public int getValueFromHand()
   {
      int valueInHand = 0;
      int numberOfAces = 0;

      for (int index = 0; index < hand.size(); index++)
      {
         Card card = hand.get(index);
         int cardValue = card.getPointValue();
         valueInHand = valueInHand + cardValue;

         if (card.getRank() == Card.Rank.ACE)
         {
            numberOfAces++;
         }
      }

      // Gives the option of Ace being 1 or 11 depending on points in hand
      while (numberOfAces > 0 && valueInHand > 21)
      {
         valueInHand = valueInHand - 10;
         numberOfAces--;
      }

      return valueInHand;
   }

   /*
    * Method gets the cards in the dealer's hand
    * 
    * @return Array List of cards in dealers hand
    */
   @Override
   public List<Card> getHand()
   {
      return new ArrayList<>(hand);
   }

   /*
    * This method clears the cards from the dealer's hand
    */
   @Override
   public void resetHand()
   {
      hand.clear();
   }

   /*
    * This method contains the logic to implement the dealer's turn
    */
   public void dealerTakesTurn()
   {
      System.out.println("\nDealer's turn.\n");

      // Reveal the face down card
      System.out.println("Dealer's hand: " + getHand());

      // Dealer hits until the hand value is at least 17
      while (getValueFromHand() <= 17)
      {
         hit();
         System.out.println(
               "Dealer draws " + getHand().get(getHand().size() - 1));
      }

      // Print the final hand
      System.out.println("Dealer's final hand is " + getHand());
   }

} // end getValueFromHand()
