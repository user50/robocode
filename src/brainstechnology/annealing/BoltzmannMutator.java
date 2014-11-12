package brainstechnology.annealing;

/**
 * Created by user50 on 01.11.2014.
 */
public class BoltzmannMutator implements Mutator {
    @Override
    public double mutate(double state, double temperature) {
        double value = 0;
        for (int i=0; i<12; i++){
            value += Math.random();
        }

        return ( value - 6 + state )*temperature;
    }

    @Override
    public double cooling(double startingTemperature, int iteration) {
        return startingTemperature/Math.log(1+iteration);
    }
}
