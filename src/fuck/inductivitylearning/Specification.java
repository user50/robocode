package fuck.inductivitylearning;

import com.example.Vector;

/**
 * Created by user50 on 24.09.2014.
 */
public class Specification {
    private Vector myPosition;
    private Vector myVelocity;

    private double distanceToEnemy;
    private double velocityTowardEnemy;
    private double velocityPerpendicularEnemy;

    public Specification(Vector myPosition, Vector myVelocity, double distanceToEnemy, double velocityTowardEnemy, double velocityPerpendicularEnemy) {
        this.myPosition = myPosition;
        this.myVelocity = myVelocity;
        this.distanceToEnemy = distanceToEnemy;
        this.velocityTowardEnemy = velocityTowardEnemy;
        this.velocityPerpendicularEnemy = velocityPerpendicularEnemy;
    }

    public Vector getMyPosition() {
        return myPosition;
    }

    public Vector getMyVelocity() {
        return myVelocity;
    }

    public double getDistanceToEnemy() {
        return distanceToEnemy;
    }

    public double getVelocityTowardEnemy() {
        return velocityTowardEnemy;
    }

    public double getVelocityPerpendicularEnemy() {
        return velocityPerpendicularEnemy;
    }
}
