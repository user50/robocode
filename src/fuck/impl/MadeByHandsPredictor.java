package fuck.impl;

import com.example.Vector;
import com.example.VectorAlgebra;
import fuck.Action;
import fuck.StateParameter;
import fuck.inductivitylearning.StatePredictor;

import java.util.HashMap;
import java.util.Map;

import static fuck.Action.*;
import static fuck.StateParameter.*;

/**
 * Created by user50 on 21.10.2014.
 */
public class MadeByHandsPredictor implements StatePredictor {
    @Override
    public Map<StateParameter, Double> nextState(Map<StateParameter, Double> state, Map<Action, Double> action) {
        double velocity = state.get(StateParameter.velocity) + Math.signum(action.get(ahead));

        Vector direction = new Vector(state.get(sinMyHeading), state.get(cosMyHeading));
        direction = VectorAlgebra.rotate(direction, action.get(rotate));

        Vector directedVelocity = VectorAlgebra.prod(direction, velocity);
        Vector coordinates = VectorAlgebra.sum(new Vector(state.get(x), state.get(y)), directedVelocity);

        Map<StateParameter, Double> nextState = new HashMap<>();
        nextState.put(StateParameter.velocity, velocity);
        nextState.put(sinMyHeading, direction.getA());
        nextState.put(cosMyHeading, direction.getB());
        nextState.put(x, coordinates.getA());
        nextState.put(y, coordinates.getB());

        nextState.put(enemyBearing, state.get(enemyBearing));
        nextState.put(distanceToEnemy, state.get(distanceToEnemy));

        nextState.put(width, state.get(width));
        nextState.put(height, state.get(height));

        return nextState;
    }

    @Override
    public void adapt(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        // no adapt
    }
}
