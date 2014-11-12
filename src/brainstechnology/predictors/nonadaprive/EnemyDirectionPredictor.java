package brainstechnology.predictors.nonadaprive;

import fuck.Action;
import fuck.StateParameter;
import algebra.Vector;
import algebra.VectorAlgebra;

import java.util.Map;

import static fuck.StateParameter.*;
import static algebra.VectorAlgebra.prod;
import static java.lang.Math.sin;

/**
 * Created by user50 on 26.10.2014.
 */
public class EnemyDirectionPredictor extends NonAdaptivePredictor {

    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        if (!state.containsKey(enemyBearing)){
            nextState.put(enemyDirectionX, 0.0);
            nextState.put(enemyDirectionY, 0.0);
            return;
        }

        Vector meToEnemy = prod(new Vector(state.get(enemyDirectionX), state.get(enemyDirectionY)), state.get(distanceToEnemy));
        Vector me = new Vector(state.get(x), state.get(y));
        Vector enemy = VectorAlgebra.sum(me, meToEnemy);

        Vector enemyVelocity = VectorAlgebra.prod(new Vector(sin(state.get(enemyHearing)), sin(state.get(enemyHearing))), state.get(StateParameter.enemyVelocity));

        Vector enemyMovement = VectorAlgebra.prod(enemyVelocity, state.get(distanceToEnemy)/20);

        Vector nextEnemyPosition = VectorAlgebra.sum(enemy, enemyMovement);

        Vector myVelocity = VectorAlgebra.prod(new Vector(state.get(sinMyHeading), state.get(cosMyHeading)), state.get(velocity));
        Vector myNextPosition = VectorAlgebra.sum(me, myVelocity);

        Vector nextMeToEnemy = VectorAlgebra.diff(nextEnemyPosition, myNextPosition);

        Vector nextEnemyDirection = VectorAlgebra.normalize(nextMeToEnemy);

        nextState.put(enemyDirectionX, nextEnemyDirection.getA());
        nextState.put(enemyDirectionY, nextEnemyDirection.getB());
    }
}
