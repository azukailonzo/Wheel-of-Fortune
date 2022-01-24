/*
    This class defines a player.
    A player has a total score (from all game rounds including the bonus), and a
    round total.
 */

public class Player {
    private int totalScore;
    private int roundTotal;

    public Player(){
        totalScore = roundTotal =  0;
    }

    public int getTotalScore() { return this.totalScore; }
    public int getRoundTotal() { return this.roundTotal; }

    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    public void setRoundTotal(int roundTotal) { this.roundTotal = roundTotal; }

}//end of class player
