package fuck.predictors;

import fuck.Action;
import fuck.StateParameter;
import fuck.inductivitylearning.SingleStateParameterPredictor;

import java.util.Map;

import static fuck.Action.ahead;

/**
 * Created by user50 on 23.10.2014.
 */
public class VelocityPredictor implements SingleStateParameterPredictor {
    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        double velocity = state.get(StateParameter.velocity) + Math.signum(action.get(ahead));
        nextState.put(StateParameter.velocity, velocity);
    }
}
