package fuck.impl;

import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.StateParameter;
import fuck.inductivitylearning.UtilityCalculator;

import java.util.Map;

import static fuck.StateParameter.*;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 21.10.2014.
 */
public class MadeByHandsUtilityCalculator implements UtilityCalculator {

    double zone = 200;

    @Override
    public double assessState(Map<StateParameter, Double> state) {
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

        utility += abs(state.get(velocity));

        if (state.get(distanceToEnemy) < 200)
            utility -= 100 - state.get(distanceToEnemy);

        if (state.get(distanceToEnemy) > 600)
            utility -= state.get(distanceToEnemy) - 600;

        Vector directionToEnemy = new Vector(state.get(enemyDirectionX), state.get(enemyDirectionY));
        Vector gunDirection = new Vector(state.get(gunSin), state.get(gunCos));

        utility += VectorAlgebra.scalarProd(directionToEnemy, gunDirection) * 500;

        return utility;
    }
}
