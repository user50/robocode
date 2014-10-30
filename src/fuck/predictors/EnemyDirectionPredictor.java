package fuck.predictors;

import fuck.Action;
import fuck.StateParameter;
import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.inductivitylearning.Handler;
import fuck.inductivitylearning.SingleStateParameterPredictor;

import java.util.Map;

import static fuck.StateParameter.*;
import static fuck.StateParameter.enemyDirectionX;
import static fuck.algebra.VectorAlgebra.prod;
import static java.lang.Math.sin;

/**
 * Created by user50 on 26.10.2014.
 */
public class EnemyDirectionPredictor implements SingleStateParameterPredictor {

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

        Vector enemyMovement = VectorAlgebra.prod(enemyVelocity, state.get(distanceToEnemy)/15);

        Vector nextEnemyPosition = VectorAlgebra.sum(enemy, enemyMovement);

        Vector myVelocity = VectorAlgebra.prod(new Vector(state.get(sinMyHeading), state.get(cosMyHeading)), state.get(velocity));
        Vector myNextPosition = VectorAlgebra.sum(me, myVelocity);

        Vector nextMeToEnemy = VectorAlgebra.diff(nextEnemyPosition, myNextPosition);

        Vector nextEnemyDirection = VectorAlgebra.normalize(nextMeToEnemy);

        nextState.put(enemyDirectionX, nextEnemyDirection.getA());
        nextState.put(enemyDirectionY, nextEnemyDirection.getB());
    }
}
