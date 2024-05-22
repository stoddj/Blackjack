package jbPak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
 * Deck that represents a a collection of cards in a deck
 * @author Sean Todd
 * @version 1.0
 */
public class Deck
{
   private List<Card> cards;

   /*
    * Constructor that instantiates a list of cards, and initializes a deck
    */
   public Deck()
   {
      cards = new ArrayList<>();
      initializeDeck();
   } // end constructor

   /*
    * This method initializes the deck creating cards that have a suit and rank
    */
   private void initializeDeck()
   {
      Card.Rank[] ranks = Card.Rank.values();
      Card.Suit[] suits = Card.Suit.values();

      // loop through a suit and assign it a rank and store it as a card
      for (int s = 0; s < suits.length; s++)
      {
         for (int r = 0; r < ranks.length; r++)
         {
            cards.add(new Card(suits[s], ranks[r]));
         } // end inner rank for loop
      } // end inner suit for loop
   } // end initializeDeck()

   /*
    * This method the shuffles the list of cards
    */
   public void shuffle()
   {
      Collections.shuffle(cards);
   } // end shuffle()

   /*
    * The method pulls one card at index 0 if the deck is not empty.
    * 
    * @return on card that has a suit and rank, or null if the deck is empty
    */
   public Card drawCard()
   {
      // Check for empty deck
      if (cards.isEmpty())
      {
         return null;
      }
      // return the top most card
      return cards.remove(0);
   } // end drawCard()

   /*
    * The method pulls a list of cards.
    * 
    * @return a list of cards
    */
   public List<Card> getCards()
   {
      return cards;
   } // end getCards()

   /*
    * The method gives the number of cards in the deck
    * 
    * @return a int value of the amount of cards the deck
    */
   public int size()
   {
      return cards.size();
   } // end size()

   /*
    * The method clears the deck of all cards, reinitialize a deck of card and
    * shuffles them
    */
   public void reset()
   {
      cards.clear();
      initializeDeck();
      shuffle();
   } // end reset()
} // end Deck class
