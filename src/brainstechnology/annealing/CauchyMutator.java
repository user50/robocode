package brainstechnology.annealing;

/**
 * Created by user50 on 01.11.2014.
 */
public class CauchyMutator implements Mutator {
    @Override
    public double mutate(double state, double temperature) {
        return state + temperature * Math.tan( Math.PI * (Math.random() - 1/2) );
    }

    @Override
    public double cooling(double startingTemperature, int iteration) {
        return startingTemperature/iteration;
    }
}
