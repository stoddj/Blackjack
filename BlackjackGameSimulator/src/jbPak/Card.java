package jbPak;

/* 
 * Class that represents a Card
 * @author Sean Todd
 * @version 1.0
 */
public class Card
{
   private Suit suit;
   private Rank rank;

   /*
    * Predefined suit constants
    */
   enum Suit
   {
      HEARTS, DIAMONDS, CLUBS, SPADES
   }

   /*
    * Predefined rank constants
    */
   enum Rank
   {
      TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING,
      ACE
   }

   /*
    * Constructor to instantiate Suit and Rank
    */
   public Card(Suit suit, Rank rank)
   {
      this.suit = suit;
      this.rank = rank;
   } // end constructor

   /*
    * This method gets suit of the card
    * 
    * @return the Suit type suit of the card
    */
   public Suit getSuit()
   {
      return suit;
   } // end getSuit()

   /*
    * This method gets rank of the card
    * 
    * @return the Rank type rank of the card
    */
   public Rank getRank()
   {
      return rank;
   } // end getRank()

   /*
    * This method gets value of the card
    * 
    * @return point value of the card
    */
   public int getPointValue()
   {
      Rank[] ranks = Rank.values();
      int[] points = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
      for (int i = 0; i < ranks.length; i++)
      {
         if (rank == ranks[i])
         {
            return points[i];
         }
      }
      return 0; // Default value if the rank is not found (e.g., joker).
   } // end getPointValue()

   /*
    * This method returns the String value of the card
    * 
    * @return String value of the card
    */
   @Override
   public String toString()
   {
      return rank + " of " + suit;
   } // end toString()
} // end Card class
