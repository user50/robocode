package brainstechnology.annealing;

/**
 * Created by user50 on 01.11.2014.
 */
public interface OptimizationProblem {

    double[] initState();

    double evaluate(double[] arguments);
}
