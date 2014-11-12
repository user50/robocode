package brainstechnology.predictors.nonadaprive;

import fuck.Action;
import fuck.StateParameter;

import java.util.Map;

import static fuck.Action.ahead;

/**
 * Created by user50 on 23.10.2014.
 */
public class VelocityPredictor extends NonAdaptivePredictor {
    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        double velocity = state.get(StateParameter.velocity) + calculateAcceleration(action.get(ahead));
        nextState.put(StateParameter.velocity, velocity);
    }

    private double calculateAcceleration(double ahead)
    {
        return 1 - 2 / (1 + Math.exp(ahead/20));
    }
}
