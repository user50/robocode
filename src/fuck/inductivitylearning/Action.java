package fuck.inductivitylearning;

/**
 * Created by user50 on 24.09.2014.
 */
public class Action {

    private double ahead;
    private double turn;

    public Action(double ahead, double turn) {
        this.ahead = ahead;
        this.turn = turn;
    }

    public double getAhead() {
        return ahead;
    }

    public double getTurn() {
        return turn;
    }
}
