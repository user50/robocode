package fuck.inductivitylearning;

import fuck.Action;
import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 23.10.2014.
 */
public interface SingleStateParameterPredictor {

    void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState);

}
