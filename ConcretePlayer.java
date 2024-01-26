public class ConcretePlayer implements Player{
    private final int number;
    private int wins;

    public ConcretePlayer(int i)
    {
        number = i;
        wins = 0;
    }
    public void addWin()
    {
        wins++;
    }

    /**
     *
     * @return true if the player is player 1, false otherwise.
     */
    public boolean isPlayerOne()
    {
        return number == 1;
    }
    /**
     * Get the number of wins achieved by the player in the game.
     *
     * @return The total number of wins by the player.
     */
    public int getWins()
    {
        return wins;
    }
}
