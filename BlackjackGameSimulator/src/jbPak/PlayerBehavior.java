package jbPak;

import java.util.List;

/* 
 * Abstract methods that define the PlayerBehavior in the game
 * @author Sean Todd
 * @version 1.0
 */
public interface PlayerBehavior
{
   /*
    * Abstract method to add card
    */
   void hit();

   /*
    * Abstract method to end turn
    */
   void stay();

   /*
    * Abstract method to get cards in hand
    */
   List<Card> getHand();

   /*
    * Abstract method to get points in had
    */
   int getValueFromHand();

   /*
    * Abstract method to reset hand
    */
   void resetHand();
}
