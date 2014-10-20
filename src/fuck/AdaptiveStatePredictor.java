package fuck;

import fuck.inductivitylearning.StatePredictor;
import regression.Constant;
import regression.Regression;
import regression.Regressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public class AdaptiveStatePredictor implements StatePredictor {

    private static final double ALPHA = 0.01;
    Map<StateParameter, Regression> regressions = new HashMap<>();

    public AdaptiveStatePredictor() {
        for (StateParameter stateParameter : StateParameter.values())
            if (stateParameter.getRegressionBuilder() != null)
                regressions.put(stateParameter, stateParameter.getRegressionBuilder().build());
    }

    @Override
    public Map<StateParameter, Double> nextState(Map<StateParameter, Double> state, Map<Action, Double> actions) {
        Map<StateParameter, Double> prediction = new HashMap<StateParameter, Double>();

        Map<String, Double> input = formInput(state, actions);

        for (StateParameter stateParameter : regressions.keySet())
            prediction.put(stateParameter, regressions.get(stateParameter).calculate(input));

        return prediction;
    }

    @Override
    public void adapt(Map<StateParameter, Double> state, Map<Action, Double> actions, Map<StateParameter, Double> nextState) {
        Map<String, Double> input = formInput(state, actions);

        for (StateParameter stateParameter : regressions.keySet()) {
            regressions.get(stateParameter).adapt(input, nextState.get(stateParameter), ALPHA);
        }

    }

    private Map<String, Double> formInput(Map<StateParameter, Double> state, Map<Action, Double> actions)
    {
        Map<String, Double> input = new HashMap<String, Double>();
        for (StateParameter stateParameter : state.keySet())
            input.put(stateParameter.name(), state.get(stateParameter));

        for (Action action : actions.keySet())
            input.put(action.name(), actions.get(action));

        return input;
    }
}
