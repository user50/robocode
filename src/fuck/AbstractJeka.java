package fuck;

import brainstechnology.Brain;
import algebra.Vector;

import java.util.HashMap;
import java.util.Map;

import static algebra.VectorAlgebra.getAngleBetween;
import static algebra.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 21.10.2014.
 */
public abstract class AbstractJeka extends AbstractFucker {

    protected Brain brain;

    @Override
    protected Map<Action, Double> specifyActions() {

        Map<Action, Double> actions = new HashMap<>();

        if (lastScannedEnemy != null){
            actions = brain.askAction(getState());

            Vector radarDirection = new Vector(sin(getRadarHeadingRadians()), cos(getRadarHeadingRadians()));
            Double radarRotateAngle = getRotateAngleToEnemy(radarDirection);

            actions.put(Action.rotateRadar, radarRotateAngle + Math.signum(radarRotateAngle) * 30 * Math.PI / 180 );

            actions.put(Action.shotPower, -(lastScannedEnemy.getDistance() - 100)/250 + 3 );
        }

        return actions;
    }

    private double getRotateAngleToEnemy(Vector vector){
        Vector myDirection = new Vector(sin(getHeadingRadians()), cos(getHeadingRadians()));
        Vector enemyDirection = rotate(myDirection, -lastScannedEnemy.getBearingRadians());

        return  getAngleBetween(vector, enemyDirection);
    }

    @Override
    protected Map<Action, Double> specifyInitActions() {
        Map<Action, Double> actions = new HashMap<Action, Double>();

        actions.put(Action.rotateRadar, 100.0);

        return actions;
    }

}
