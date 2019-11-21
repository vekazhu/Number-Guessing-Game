
/**
 * Player class stores information of players related to the game.
 * It stores names, cumulative scores and last guesses made by players.
 * 
 * @Weijia ZHU
 * @10/Apr/2018
 */
public class Player
{
    //the cumulative score of the player
    private int cumulativeScore;
    //the last guess made by human player in the current round
    private int lastGuess;
    //the name of the player
    private String playerName;
    
    /**
     * Default Constructor for class Player, it is used to initialize an 
     * object of this class, and has no parameters.
     */
    public Player()
    { 
        cumulativeScore = 0;
        lastGuess = 0;
        playerName = " ";
    }
    
    /**
     * Non-default constructor that accepts a value for the name of the
     * player.
     */
    public Player(String newPlayerName)
    {
        cumulativeScore = 0;
        lastGuess = 0;
        playerName = newPlayerName;
    }
    
    /**
     * CumulativeScore accessor that used to obtain the cumulative score of 
     * players.
     */
    public int getScore()
    {
        return cumulativeScore;
    }
    
    /**
     * LastGuess accessor that used to obtain the last guesses made by
     * players.
     */
    public int getGuess()
    {
        return lastGuess;
    }
    
    /**
     * PlayerName accessor that used to obtain the name of players.
     */
    public String getName()
    {
        return playerName;
    }
    
    /**
     * CumulativeScore mutator that used to update the cumulative scores of 
     * players.
     * 
     * @param newCumulativeScore: the total score of player up to the point 
     * the method is called.
     */
    public void setScore(int newCumulativeScore)
    {
        cumulativeScore = newCumulativeScore;
    }
    
    /**
     * LastGuess mutator that used to update the last guess made by players.
     * 
     * @param newLastGuess: the last guess of player up to the point the
     * method is called.
     */
    
    public void setGuess(int newLastGuess)
    {
        lastGuess = newLastGuess;
    }
    
    /**
     * PlayerName mutator that used to change the name of players.
     * 
     * @param newPlayerName: the player name that is entered by the player.
     */
    public void setName(String newPlayerName)
    {
        playerName = newPlayerName;
    }
    
}


