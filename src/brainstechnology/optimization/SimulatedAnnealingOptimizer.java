package brainstechnology.optimization;

import brainstechnology.annealing.AnnealingSimulation;
import brainstechnology.annealing.CauchyMutator;
import brainstechnology.annealing.OptimizationProblem;
import brainstechnology.inductivitylearning.Function;
import brainstechnology.inductivitylearning.Optimiser;
import fuck.Action;
import fuck.ActionUtil;

import java.util.Map;

/**
 * Created by user50 on 25.10.2014.
 */
public class SimulatedAnnealingOptimizer implements Optimiser {


    @Override
    public Map<Action, Double> findOptimalSolution(Function<Map<Action, Double>> function) {

        AnnealingSimulation simulation = new AnnealingSimulation(new OptimalActionProblem(function), new CauchyMutator());
        simulation.setStartingTemperature(0.01);
        simulation.setMaxIteration(300);

        double[] solution = simulation.search();

        return toMap(solution);
    }

    private  class OptimalActionProblem implements OptimizationProblem
    {

        Function<Map<Action, Double>> function;

        private OptimalActionProblem(Function<Map<Action, Double>> function) {
            this.function = function;
        }

        @Override
        public double[] initState() {
            double[] state = new double[Action.values().length];
            for (int i = 0; i < state.length; i++)
                state[i] = Math.random();

            return state;
        }

        @Override
        public double evaluate(double[] arguments) {
            return -function.calculate(toMap(arguments));
        }
    }

    private Map<Action, Double> toMap(double[] arguments)
    {
        for (int i = 0; i < arguments.length; i++)
            arguments[i] = Math.tanh(arguments[i]);

        Map<Action, Double> actions = ActionUtil.toMap(arguments);
        Action.denormalise(actions);

        return actions;
    }

}
