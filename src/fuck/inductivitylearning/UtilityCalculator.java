package fuck.inductivitylearning;

import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 24.09.2014.
 */
public interface UtilityCalculator {

    public double assessState(Map<StateParameter, Double> state);
}
