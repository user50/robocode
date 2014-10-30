package fuck.impl;

import fuck.StateParameter;
import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.inductivitylearning.UtilityCalculator;

import java.util.Map;

import static fuck.algebra.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 26.10.2014.
 */
public class GunUtilityCalculator implements UtilityCalculator {
    @Override
    public double assessState(Map<StateParameter, Double> state) {
        if (!state.containsKey(StateParameter.enemyBearing))
            return 0;

        Vector gunDirection = new Vector(state.get(StateParameter.gunSin), state.get(StateParameter.gunCos));

        Vector myDirection = new Vector(state.get(StateParameter.sinMyHeading), state.get(StateParameter.cosMyHeading));
        Vector enemyDirection = rotate(myDirection, -state.get(StateParameter.enemyBearing));

        return VectorAlgebra.scalarProd(gunDirection, enemyDirection);
    }
}
