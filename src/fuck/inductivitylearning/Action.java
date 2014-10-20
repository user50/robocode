package fuck.inductivitylearning;

/**
 * Created by user50 on 24.09.2014.
 */
public class Action {

    private Double ahead;
    private Double turn;
    private Double turnRadar;
    private Double turnGun;
    private Double shotPower;


    public double getAhead() {
        return ahead;
    }

    public double getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return ahead + ";" + turn;
    }
}
