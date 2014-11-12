package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;

import java.util.Map;

import static fuck.StateParameter.velocity;
import static java.lang.Math.abs;

/**
 * Created by user50 on 02.11.2014.
 */
public class KeepHighVelocity implements UtilityAssessor {
    @Override
    public double asses(Map<StateParameter, Double> state) {

        return abs(state.get(velocity));
    }
}
