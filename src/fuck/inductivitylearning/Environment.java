package fuck.inductivitylearning;

import com.example.Vector;

/**
 * Created by user50 on 24.09.2014.
 */
public class Environment {
    private double myVelocity;
    private double myHearing;
    private double x;
    private double y;

    public Environment(double myVelocity, double myHearing, double x, double y) {
        this.myVelocity = myVelocity;
        this.myHearing = myHearing;
        this.x = x;
        this.y = y;
    }

    public double getMyVelocity() {
        return myVelocity;
    }

    public double getMyHearing() {
        return myHearing;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return myVelocity + ";" + myHearing + ";" + x + ";" + y;
    }
}
