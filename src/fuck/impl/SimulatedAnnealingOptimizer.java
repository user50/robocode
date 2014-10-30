package fuck.impl;

import fuck.Action;
import fuck.inductivitylearning.Function;
import fuck.inductivitylearning.Optimiser;
import net.sourceforge.jannealer.AnnealingScheme;
import net.sourceforge.jannealer.ObjectiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 25.10.2014.
 */
public class SimulatedAnnealingOptimizer implements Optimiser {

    @Override
    public Map<Action, Double> findOptimalSolution(final Function<Map<Action, Double>> function) {

        AnnealingScheme scheme = new AnnealingScheme();
        scheme.setCoolingRate(150);


        final ObjectiveFunction objective = new ObjectiveFunction() {
            @Override
            public int getNdim() {
                return 3;
            }

            @Override
            public double distance(double[] doubles) {
                Map<Action, Double> arguments = toMap(doubles);

                return -function.calculate(arguments);
            }
        };

        scheme.setFunction(objective);
        scheme.anneal();

        return toMap(scheme.getSolution());
    }

    private Map<Action, Double> toMap(double[] doubles)
    {
        Map<Action, Double> arguments = Action.generateRandomAction();

        arguments.put(Action.ahead, doubles[0]);
        arguments.put(Action.rotate, normalise(doubles[1]));
        arguments.put(Action.rotateGun, normalise(doubles[2]));

        return arguments;
    }

    private double[] toArray(Map<Action, Double> arguments)
    {
        double[] values = new double[Action.values().length];

        int i = 0;
        for (Action action : Action.values())
            values[i++] = arguments.get(action);

        return values;
    }

    private double normalise(double value)
    {
        return (1 - 1/(1 + Math.exp(value))) * 2 * Math.PI - Math.PI;
    }

}
