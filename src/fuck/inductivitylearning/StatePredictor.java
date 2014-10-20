package fuck.inductivitylearning;


import fuck.*;

import java.util.Map;

/**
 * Created by user50 on 24.09.2014.
 */
public interface StatePredictor {

    public Map<StateParameter, Double> nextState(Map<StateParameter, Double> state, Map<fuck.Action, Double> action);

    public void adapt(Map<StateParameter, Double> state, Map<fuck.Action, Double> action, Map<StateParameter, Double> nextState);
}
