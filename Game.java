import java.util.*;

/**
 * The game class manges the play of the game by including methods needed. It proceeds
 * the game as required by the assignemnt.
 * 
 * @Weijia ZHU
 * @20.04.2018
 */
public class Game 
{
    //The field of player 1, the computer player.
    private Player computerPlayer;
    //The field of player 2, the human player.
    private Player humanPlayer;
    //The field for randon number generator to generate numbers needed.
    private RandomNumberGenerator randomNumber;
    //The field for upper limit of the range of numbers can be guessed from.
    private int upperLimit;
    //The field for lower limit of the range of numbers can be guessed from.
    private int lowerLimit;
    //The field for storing the number of attempts made by computer player.
    private int computerAttempts;
    //The field for storing the number of attempts made by human player.
    private int humanAttempts;

    /**
     * Constructor for class Game to initialize objects.
     */
    public Game() 
    {
        // initialise instance variables
        computerPlayer = new Player("Computer");
        humanPlayer = new Player(" ");
        randomNumber = new RandomNumberGenerator();
        upperLimit = 100;
        lowerLimit = 1;
        computerAttempts = 3;
        humanAttempts = 3;
    }

    /**
     * Generates an integer abandonRoundDecider in this method and parameter is abandonRoundIndicator,
     * and if they match, returns true.
     * 
     * @return boolean
     */
    public boolean computerAbandonOrNot(int abandonRoundIndicator) 
    {
        int abandonRoundDecider = randomNumber.generateRandomNumber(1, 20);
        return abandonRoundDecider == abandonRoundIndicator;
    }
    
    /**
     * Generates an integer computerGuess in this method and parameter is abandonRoundIndicator and result, if computerGuess 
     * matches abandonRoundIndicator the computer player will abandon a round and this method return ture. If the computerGuess
     * matches the result it will also return true.
     * 
     * @return boolean
     */
    public boolean computeGuess(int abandonRoundIndicator, int result) 
    {
        if (computerAbandonOrNot(abandonRoundIndicator)) 
        {
            System.out.println("Computer decided to abandon this round.");
            return true;
        } 
        else 
        {
            int computerGuess = randomNumber.generateRandomNumber(lowerLimit, upperLimit);
            computerPlayer.setGuess(computerGuess);
            if (computerGuess == result) 
            {
                System.out.println("Computer player wins this round! The correct number is " + result + ".");
                computerPlayer.setScore(computerPlayer.getScore() + getNewScore(humanAttempts, computerAttempts));
                return true;
            } 
            else 
            {
                computerAttempts++;
                System.out.println("Computer player guessed " + computerGuess + ".");
                if (computerAttempts + humanAttempts < 6) 
                {
                    if (computerGuess > result) 
                    {
                        upperLimit = computerGuess - 1;
                        System.out.println(computerGuess + " is larger than the answer, please guess from " + lowerLimit
                                           + " to " + upperLimit + ".");
                    } 
                    else 
                    {
                        lowerLimit = computerGuess + 1;
                        System.out.println(computerGuess + " is smaller than the answer, please guess from "
                                           + lowerLimit + " to " + upperLimit + ".");
                    }
                }
                return false;
            }
        }
    }
    
    /**
     * A method used to add score to players according to attempts they made when they 
     * get the correct number.
     * 
     * @param humanAttempts: the number of attempts of human player
     * @param computerAttempts: the number of attempts of computer player
     */
    public int getNewScore(int humanAttempts, int computerAttempts) 
    {
        int totalAttempts = humanAttempts + computerAttempts;
        switch (totalAttempts) 
        {
            case 1:
                return 20;
            case 2:
                return 15;
            case 3:
                return 11;
            case 4:
                return 8;
            case 5:
                return 6;
            case 6:
                return 5;
            default:
                return 0;
        }
    }
    
    /**
     * Generate an integer humanGuess in this method and parameter is result, and if they match,
     * return true.
     * 
     * @return boolean
     */
    public boolean humanGuess(int result) 
    {
        int humanGuess = numberScanner(lowerLimit, upperLimit);
        
        if (humanGuess == 999) 
        {
            return true;
        }
        
        humanPlayer.setGuess(humanGuess);
        
        if (humanGuess == result) 
        {
            System.out.println("Fantastic! You win this round!");
            humanPlayer.setScore(humanPlayer.getScore() + getNewScore(humanAttempts, computerAttempts));
            return true;
        } 
        else 
        {
            humanAttempts++;
            
            if (computerAttempts + humanAttempts < 6) 
            {
                if (humanGuess > result) 
                {
                    System.out.print("Your guess " + humanGuess + " is larger than the correct number.");
                    upperLimit = humanGuess - 1;
                    System.out.println("Computer player will now guess from " + lowerLimit + " to " + upperLimit + ".");
                } 
                else 
                {
                    System.out.print("Your guess " + humanGuess + " is smaller than the correct number.");
                    lowerLimit = humanGuess + 1;
                    System.out.println("Computer player will now guess from " + lowerLimit + " to " + upperLimit + ".");

                }
            }
            
            return false;
        }
    }

    
    /**
     * Method for player to enter their name.
     */
    public void nameScanner() 
    {

        Scanner nameScanner = new Scanner(System.in);
        System.out.println("Please enter your name from 1 to 8 characters long with no spaces to begin the game.");

        while (nameScanner.hasNextInt() == true) 
        {
            System.out.println("Please enter characters only.");
            nameScanner.nextInt();
        }

        String playerName = nameScanner.next().trim();

        while (playerName.length() > 8) 
        {
            System.out.println("Please enter your name as required.");

            while (nameScanner.hasNextInt() == true) 
            {
                System.out.println("Please enter characters only.");
                nameScanner.nextInt();
            }
            
            playerName = nameScanner.next().trim();
        }

        humanPlayer.setName(playerName);
        System.out.println("Hello, " + playerName + " , nice to meet you! Game begins now.");
        nameScanner.close();
    }

    /**
     * The method used to enter the guess of human player, and make sure the input meets requirements.
     * 
     * @param lowerLimit: the minimum number of the range of numbers given.
     * @param upperLimit: the maximum number of the range of numbers given.
     */
    public int numberScanner(int lowerLimit, int upperLimit) 
    {
        Scanner numberScanner = new Scanner(System.in);
        System.out.println("Please enter a number (your guess) from " + lowerLimit + " to " + upperLimit
                           + ", or 999 to abandon a round.");

        while (numberScanner.hasNextInt() == false) 
        {
            System.out.println("Please renter an integer (a number).");
            numberScanner.next();
        }
      
        int enteredNumber = numberScanner.nextInt();

        if (enteredNumber == 999) 
        {
            System.out.println("You chose to abandon this round.");
            numberScanner.close();
            return enteredNumber;
        }

        while (enteredNumber < 1 || 
               enteredNumber > 100) 
        {
            System.out.println("Please make sure the number you entered within the given range.");
            enteredNumber = numberScanner.nextInt();

        }

        if (enteredNumber < lowerLimit || 
            enteredNumber > upperLimit) 
        {
            System.out.println("You wasted one guess, because the number is out of the given range.");
        }

        numberScanner.close();
        return enteredNumber;
    }

    /**
     * The method used to play the game.
     */
    public void start() 
    {
        int roundCount = 1;
        System.out.println("Welcome to the number guessing game!");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                                  RULES");
        System.out.println(
                "1. You will race with the computer player to see who can guess the hidden number by using less guesses.");
        System.out.println(
                "2. The game has 4 rounds in total, and each round you and the computer player both have 3 guesses.");
        System.out.println(
                "3. The hidden number will be chosen randomly by the computer, but the computer player will not know it.");
        System.out.println("4. Computer will also decide you or the computer player can have the first guess.");
        System.out.println("5. The less guesses you use before getting the correct number, more scores you will get.");
        System.out.println(
                "6. At the end of the each round, if on one gets the correct number, score will be given to you (score = 10 - proximity-of-last-guess).");
        System.out.println("7. Before each of your guess, you can enter number 999 to abandon the round.");
        System.out.println("8. Please read each instruction carefully before performing.");
        System.out.println("-----------------------------------------------------------------------------------");
        nameScanner();

        while (roundCount < 5) 
        {
            computerAttempts = 0;
            humanAttempts = 0;
            lowerLimit = 1;
            upperLimit = 100;
            int result = randomNumber.generateRandomNumber(1, 100);
            int playerTurnDecider = randomNumber.generateRandomNumber(1, 2);
            int abandonRoundIndicator = randomNumber.generateRandomNumber(1, 20);

            if (computerAttempts == 0 && 
                humanAttempts == 0)
            {
                System.out.println("  ");
                System.out.println("Round " + roundCount + " begins now!");
            }

            while (computerAttempts < 3 && 
                   humanAttempts < 3) 
            {
                if (playerTurnDecider == 1) 
                {
                    if (computeGuess(abandonRoundIndicator, result) == true) 
                    {
                        computerAttempts++;
                        roundCount++;
                        break;
                    } 
                    else 
                    {
                        if (humanGuess(result) == true) 
                        {
                            humanAttempts++;
                            roundCount++;
                            break;
                        }
                    }
                } 
                else 
                {
                    if (humanGuess(result) == true) 
                    {
                        humanAttempts++;
                        roundCount++;
                        break;
                    } 
                    else 
                    {
                        if (computeGuess(abandonRoundIndicator, result) == true) 
                        {
                            humanAttempts++;
                            roundCount++;
                            break;
                        }
                    }
                }

                if (computerAttempts + humanAttempts == 6)

                {   
                    System.out.println("What a pity, the correct number is " + result + ".");
                    int computerPityScore = 10 - Math.abs(result - computerPlayer.getGuess());
                    int humanPityScore = 10 - Math.abs(result - humanPlayer.getGuess());
                    roundCount++;

                    if (computerPityScore > 0 && 
                        computerPityScore < 10) 
                    {
                        computerPlayer.setScore((computerPlayer.getScore() + computerPityScore));
                        System.out.println("Computer player scored " + computerPityScore + ".");
                    }

                    if (humanPityScore > 0 && 
                        humanPityScore < 10) 
                    {
                        humanPlayer.setScore((humanPlayer.getScore() + humanPityScore));
                        System.out.println("Human player scored " + humanPityScore + ".");
                    }
                }
            }
           
            if (roundCount == 5) 
            {
                System.out.println(" ");
                System.out.println("The total score of the Computer player in this game is " + computerPlayer.getScore() + "." );
                System.out.println("The total score of the Human player in this game is " + humanPlayer.getScore() + "." );
                    if (computerPlayer.getScore() > humanPlayer.getScore())
                    {
                        System.out.println("Computer player wins the game, thank you for playing.");
                    }
                    else
                    {
                        System.out.println("You win the game! Thank you for playing!");
                    }
            }
        }
    }
}
