package fuck.inductivitylearning;

import fuck.State;

/**
 * Created by user50 on 24.09.2014.
 */
public interface StatePredictor {

    public Environment nextState(Environment state, Action action);
}
