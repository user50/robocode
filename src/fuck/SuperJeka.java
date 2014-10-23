package fuck;

import com.example.Vector;
import fuck.impl.MadeByHandsPredictor;
import fuck.impl.MadeByHandsUtilityCalculator;
import fuck.impl.SimplestOptimiser;
import fuck.inductivitylearning.PreProcess;
import fuck.preprocessors.Hearing2sinAndCos;
import robocode.ScannedRobotEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.VectorAlgebra.getAngleBetween;
import static com.example.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 21.10.2014.
 */
public class SuperJeka extends AssKicker {

    public SuperJeka()
    {
        super(new MadeByHandsPredictor(), new SimplestOptimiser(), new MadeByHandsUtilityCalculator(), new PreProcess().with(new Hearing2sinAndCos()));
    }

    @Override
    protected Map<Action, Double> makeAction() {
        Map<Action, Double> actions = super.makeAction();

        if (lastScannedEnemy != null){
            Vector radarDirection = new Vector(sin(getRadarHeadingRadians()), cos(getRadarHeadingRadians()));
            Double radarRotateAngle = getRotateAngleToEnemy(radarDirection);

            actions.put(Action.rotateRadar, radarRotateAngle + Math.signum(radarRotateAngle) * 30 * Math.PI / 180 );

            Vector gunDirection = new Vector(sin(getGunHeadingRadians()), cos(getGunHeadingRadians()));
            Double gunRotateAngle = getRotateAngleToEnemy(gunDirection);

            setTurnGunLeftRadians(gunRotateAngle);
            actions.put(Action.rotateGun, gunRotateAngle);

            actions.put(Action.shotPower, 1.2);
        }

        return actions;
    }

    private double getRotateAngleToEnemy(Vector vector){
        Vector myDirection = new Vector(sin(getHeadingRadians()), cos(getHeadingRadians()));
        Vector enemyDirection = rotate(myDirection, -lastScannedEnemy.getBearingRadians());

        return  getAngleBetween(vector, enemyDirection);
    }

    @Override
    protected Map<Action, Double> initAction() {
        Map<Action, Double> actions = new HashMap<Action, Double>();

        actions.put(Action.rotateRadar, 100.0);

        return actions;
    }

}
