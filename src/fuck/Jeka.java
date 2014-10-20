package fuck;

import com.example.Vector;
import fuck.inductivitylearning.StatePredictor;
import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.example.VectorAlgebra.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 11.10.2014.
 */
public class Jeka extends AbstractFucker {

    private Double enemyBearing = null;

    @Override
    protected Map<Action, Double> initAction() {
        Map<Action, Double> actions = new HashMap<Action, Double>();

        actions.put(Action.rotateRadar, 100.0);

        return actions;
    }

    @Override
    protected Map<Action, Double> makeAction() {
        Map<Action, Double> actions = new HashMap<Action, Double>();

        if (enemyBearing != null){
            Vector radarDirection = new Vector(sin(getRadarHeadingRadians()), cos(getRadarHeadingRadians()));
            Double radarRotateAngle = getRotateAngleToEnemy(radarDirection);

            actions.put(Action.rotateRadar, radarRotateAngle + Math.signum(radarRotateAngle) * 10 * Math.PI / 180 );

            Vector gunDirection = new Vector(sin(getGunHeadingRadians()), cos(getGunHeadingRadians()));
            Double gunRotateAngle = getRotateAngleToEnemy(gunDirection);

            setTurnGunLeftRadians(gunRotateAngle);
            actions.put(Action.rotateGun, gunRotateAngle);

            actions.put(Action.shotPower, 1.2);

            Vector myDirection = new Vector(sin(getHeadingRadians()), cos(getHeadingRadians()));
            Vector enemyDirection = rotate(myDirection, -enemyBearing);
            double bodyRotateAngle = getAngleBetween(myDirection, normal(enemyDirection));

            actions.put(Action.rotate, bodyRotateAngle);

            actions.put(Action.ahead, (Math.random() - 0.5) * 200 );
        }



        return actions;
    }

    private double getRotateAngleToEnemy(Vector vector){
        Vector myDirection = new Vector(sin(getHeadingRadians()), cos(getHeadingRadians()));
        Vector enemyDirection = rotate(myDirection, -enemyBearing);

        return  getAngleBetween(vector, enemyDirection);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        enemyBearing = event.getBearingRadians();
    }

    @Override
    public void onPaint(Graphics2D g) {
        super.onPaint(g);
    }
}
