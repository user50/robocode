package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 02.11.2014.
 */
public class AvoidWall implements UtilityAssessor{

    private final static double WEIGHT = 200;

    @Override
    public double asses(Map<StateParameter, Double> state) {
        double utility = 0;
        double x = state.get(StateParameter.x);
        double y = state.get(StateParameter.y);

        double width = state.get(StateParameter.width);
        double height = state.get(StateParameter.height);

        utility += WEIGHT * calculate(x);
        utility += WEIGHT * calculate(width - x);
        utility += WEIGHT * calculate(y);
        utility += WEIGHT * calculate(height - y);

        return utility;
    }

    private double calculate(double value)
    {
        return 1 - 1 / ( 1 + Math.exp( (value-100) / 20 ) );
    }
}
