package fuck.impl;

import cc.mallet.optimize.*;
import fuck.Action;
import fuck.inductivitylearning.Function;
import fuck.inductivitylearning.Optimiser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 25.10.2014.
 */
public class MalletOptimizer implements Optimiser {
    @Override
    public Map<Action, Double> findOptimalSolution(Function<Map<Action, Double>> function) {
        FitnessFunction optimizable = new FitnessFunction(function, Action.generateRandomAction() );


        try {
            Optimizer optimizer = new GradientAscent(optimizable);

            boolean converged = false;
            converged = optimizer.optimize();
        } catch (Exception e) {
            // This exception may be thrown if L-BFGS
            //  cannot step in the current direction.
            // This condition does not necessarily mean that
            //  the optimizer has failed, but it doesn't want
            //  to claim to have succeeded...
        }

        return optimizable.getSolution();
    }

    private static class FitnessFunction implements Optimizable.ByGradientValue
    {
        Function<Map<Action, Double>> function;
        Map<Action, Double> solution;
        double delta = 0.0001;

        private FitnessFunction(Function<Map<Action, Double>> function, Map<Action, Double> solution) {
            this.function = function;
            this.solution = solution;
        }

        @Override
        public void getValueGradient(double[] doubles) {
            double value = getValue();

            int i = 0;
            for (Action action : Action.values()) {
                Map<Action, Double> clone = new HashMap<>(solution);
                clone.put(action, solution.get(action) + delta );
                doubles[i++] = -(function.calculate(clone) - value) / delta;
            }
        }

        @Override
        public double getValue() {
            return -function.calculate(solution);
        }

        @Override
        public int getNumParameters() {
            return Action.values().length;
        }

        @Override
        public void getParameters(double[] buffer) {
            int i = 0;
            for (Action action : Action.values())
                buffer[i++] = solution.get(action);
        }

        @Override
        public double getParameter(int i) {
            return solution.get(Action.values()[i]);
        }

        @Override
        public void setParameters(double[] doubles) {
            int i = 0;
            for (Action action : Action.values())
                solution.put(action, doubles[i++]);
        }

        @Override
        public void setParameter(int i, double v) {
            solution.put(Action.values()[i], v);
        }

        public Map<Action, Double> getSolution() {
            return solution;
        }
    }


}
