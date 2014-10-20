package fuck;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by user50 on 19.10.2014.
 */
public class Snapshot implements Serializable{

    public Map<Action, Double> actions;
    public Map<StateParameter, Double> state;
    public Map<StateParameter, Double> nextState;

    public Snapshot(Map<Action, Double> actions, Map<StateParameter, Double> state, Map<StateParameter, Double> nextState) {
        this.actions = actions;
        this.state = state;
        this.nextState = nextState;
    }
}
