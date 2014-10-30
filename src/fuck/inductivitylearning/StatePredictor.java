package fuck.inductivitylearning;


import fuck.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user50 on 24.09.2014.
 */
public class StatePredictor {

    protected List<SingleStateParameterPredictor> predictors = new ArrayList<>();

    public StatePredictor() {
    }

    public StatePredictor(List<SingleStateParameterPredictor> predictors) {
        this.predictors = predictors;
    }

    public Map<StateParameter, Double> nextState(Map<StateParameter, Double> state, Map<Action, Double> action)
    {
        Map<StateParameter, Double> nextState = new HashMap<>(state);

        for (SingleStateParameterPredictor predictor : predictors)
            predictor.calculate(state, action, nextState);

        return nextState;
    }

    public StatePredictor with(SingleStateParameterPredictor predictor)
    {
        predictors.add(predictor);

        return this;
    }

}
