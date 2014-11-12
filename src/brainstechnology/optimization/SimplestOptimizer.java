package brainstechnology.optimization;

import brainstechnology.inductivitylearning.Function;
import brainstechnology.inductivitylearning.Optimiser;
import fuck.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 21.10.2014.
 */
public class SimplestOptimizer implements Optimiser {
    private static final int ITERATIONS = 300;

    @Override
    public Map<Action, Double> findOptimalSolution(Function<Map<Action, Double>> function) {
        Map<Action, Double> best = new HashMap<>();
        double bestUtility = -Double.MAX_VALUE;

        for (int i=0;i<ITERATIONS;i++) {
            Map<Action, Double> decision =  Action.generateRandomAction();
            double utility = function.calculate(decision);

            if (utility > bestUtility) {
                best = decision;
                bestUtility = utility;
            }
        }

        return best;
    }


}
