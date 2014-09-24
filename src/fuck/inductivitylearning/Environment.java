package fuck.inductivitylearning;

import com.example.Vector;

/**
 * Created by user50 on 24.09.2014.
 */
public class Environment {
    private Vector myPosition;
    private Vector myVelocity;

    public Environment(Vector myPosition, Vector myVelocity) {
        this.myPosition = myPosition;
        this.myVelocity = myVelocity;
    }

    public Vector getMyPosition() {
        return myPosition;
    }

    public Vector getMyVelocity() {
        return myVelocity;
    }
}
