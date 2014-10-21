package fuck.impl;

import fuck.StateParameter;
import fuck.inductivitylearning.UtilityCalculator;

import java.util.Map;

import static fuck.StateParameter.velocity;
import static fuck.StateParameter.width;
import static fuck.StateParameter.x;

/**
 * Created by user50 on 21.10.2014.
 */
public class MadeByHandsUtilityCalculator implements UtilityCalculator {

    double zone = 200;

    @Override
    public double calculate(Map<StateParameter, Double> state) {
        double utility = 0;
        double x = state.get(StateParameter.x);
        double y = state.get(StateParameter.y);

        double width = state.get(StateParameter.width);
        double height = state.get(StateParameter.height);

        if (x - zone < 0)
            utility += x - zone;

        if (y - zone < 0)
            utility += y - zone;

        if ( width - x - zone < 0)
            utility += width - x - zone;

        if ( height - y - zone < 0)
            utility += width - y - zone;

        utility += Math.abs(state.get(velocity));

        return utility;
    }
}
