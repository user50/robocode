package brainstechnology.predictors.nonadaprive;

import brainstechnology.inductivitylearning.SingleStateParameterPredictor;
import fuck.Action;
import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 08.11.2014.
 */
public abstract class NonAdaptivePredictor implements SingleStateParameterPredictor {

    @Override
    public void adapt(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {

    }
}
