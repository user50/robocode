package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;

import java.util.Map;

import static fuck.StateParameter.distanceToEnemy;

/**
 * Created by user50 on 02.11.2014.
 */
public class KeepDistanceToEnemy implements UtilityAssessor {

    private double minDistance = 200;

    @Override
    public double asses(Map<StateParameter, Double> state) {
        if (state.get(distanceToEnemy) < minDistance)
            return state.get(distanceToEnemy) - 200;

        return 0;
    }
}
