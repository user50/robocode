package fuck;

import fuck.algebra.Vector;
import fuck.impl.*;
import fuck.inductivitylearning.PreProcess;
import fuck.preprocessors.DirectionToEnemy;
import fuck.preprocessors.GunHearing2GunDirection;
import fuck.preprocessors.Hearing2Direction;

import java.util.HashMap;
import java.util.Map;

import static fuck.algebra.VectorAlgebra.getAngleBetween;
import static fuck.algebra.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 21.10.2014.
 */
public class SuperJeka extends AbstractFucker {

    Brain brain;

    public SuperJeka()
    {
        brain = new Brain(new MadeByHandsPredictor(), new SimplestOptimizer(), new MadeByHandsUtilityCalculator(),
                new PreProcess()
                .with(new Hearing2Direction())
                .with(new GunHearing2GunDirection())
                .with(new DirectionToEnemy())
        );
    }

    @Override
    protected Map<Action, Double> specifyActions() {
        Map<Action, Double> actions = brain.askAction(getState());

        if (lastScannedEnemy != null){
            Vector radarDirection = new Vector(sin(getRadarHeadingRadians()), cos(getRadarHeadingRadians()));
            Double radarRotateAngle = getRotateAngleToEnemy(radarDirection);

            actions.put(Action.rotateRadar, radarRotateAngle + Math.signum(radarRotateAngle) * 30 * Math.PI / 180 );
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
    protected Map<Action, Double> specifyInitActions() {
        Map<Action, Double> actions = new HashMap<Action, Double>();

        actions.put(Action.rotateRadar, 100.0);

        return actions;
    }

}
