package brainstechnology.predictors.nonadaprive;

import fuck.Action;
import fuck.StateParameter;
import algebra.Vector;
import algebra.VectorAlgebra;

import java.util.Map;

import static fuck.StateParameter.*;
import static java.lang.Math.sin;

/**
 * Created by user50 on 25.10.2014.
 */
public class DistanceToEnemyPredictor extends NonAdaptivePredictor {
    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        if (!state.containsKey(enemyBearing)) {
            nextState.put(distanceToEnemy, 0.0);

            return;
        }

        Vector myPosition = new Vector(state.get(StateParameter.x), state.get(StateParameter.y));
        Vector directionToEnemy = new Vector(sin(state.get(enemyBearing)), Math.cos(state.get(enemyBearing)));

        Vector enemy = VectorAlgebra.sum(myPosition, VectorAlgebra.prod(directionToEnemy, state.get(distanceToEnemy)));

        Vector myNextPosition = new Vector(nextState.get(x), nextState.get(y));

        nextState.put(distanceToEnemy, VectorAlgebra.length(VectorAlgebra.diff(enemy, myNextPosition)));
    }
}
