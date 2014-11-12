package brainstechnology.annealing;

/**
 * Created by user50 on 01.11.2014.
 */
public interface Mutator {

    double mutate(double state, double temperature );

    double cooling(double startingTemperature, int iteration);

}
