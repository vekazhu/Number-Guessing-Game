import java.util.Random;
/**
 * This class is a generator for random numbers that will be used in the game.
 * 
 * @Weijia ZHU
 * @13/04/2018
 */

public class RandomNumberGenerator
{
   private Random random;
   
   /**
     * Default constructor for class RandomNumberGenerator.
     */
    public RandomNumberGenerator()
    {
        random = new Random();
    }
    
    
   /**
    * Method that generate random number from lower limit to upper limit.
    * Both min and max are inclusive
    */
   public int generateRandomNumber(int lowerLimit, int upperLimit)
   {
       return random.nextInt((upperLimit - lowerLimit) + 1) + lowerLimit;
   }
   
    
    










}
