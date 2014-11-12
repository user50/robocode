package brainstechnology.inductivitylearning;

import fuck.Action;

import java.util.Map;

/**
 * Created by user50 on 21.10.2014.
 */
public interface Optimiser {

    public Map<fuck.Action, Double> findOptimalSolution(Function<Map<Action, Double>> function);

}
