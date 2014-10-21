package fuck.impl;

import fuck.Action;
import fuck.inductivitylearning.Function;
import fuck.inductivitylearning.Optimiser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 21.10.2014.
 */
public class SimplestOptimiser implements Optimiser {
    private static final int ITERATIONS = 30;

    @Override
    public Map<Action, Double> solve(Function<Map<Action, Double>> function) {
        Map<Action, Double> best = new HashMap<>();
        double bestUtility = -Double.MAX_VALUE;

        for (int i=0;i<ITERATIONS;i++) {
            Map<Action, Double> decision =  generateRandomAction();
            double utility = function.calculate(decision);

            if (utility > bestUtility) {
                best = decision;
                bestUtility = utility;
            }
        }

        return best;
    }

    private Map<Action, Double> generateRandomAction()
    {
        Map<Action, Double> decision = new HashMap<>();

        for (Action action : Action.values())
            decision.put(action, action.generateRandomValidValue());

        return decision;
    }
}
