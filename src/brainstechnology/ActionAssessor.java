package brainstechnology;

import brainstechnology.inductivitylearning.Function;
import brainstechnology.inductivitylearning.StatePredictor;
import brainstechnology.inductivitylearning.UtilityFunction;
import fuck.Action;
import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 08.11.2014.
 */
public class ActionAssessor implements Function<Map<Action,Double>> {

    StatePredictor statePredictor;
    UtilityFunction utilityFunction;
    Map<StateParameter, Double> state;

    public ActionAssessor(StatePredictor statePredictor, UtilityFunction utilityFunction, Map<StateParameter, Double> state) {
        this.statePredictor = statePredictor;
        this.utilityFunction = utilityFunction;
        this.state = state;
    }

    @Override
    public double calculate(Map<Action, Double> actions) {
        Map<StateParameter, Double> nextState = statePredictor.nextState(state, actions);

        return utilityFunction.compute(nextState);
    }
}