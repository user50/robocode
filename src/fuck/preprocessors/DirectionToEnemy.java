package fuck.preprocessors;

import fuck.StateParameter;
import fuck.algebra.Vector;
import fuck.inductivitylearning.Handler;

import java.util.Map;

import static fuck.algebra.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 26.10.2014.
 */
public class DirectionToEnemy implements Handler {
    @Override
    public void process(Map<StateParameter, Double> state) {
        if (!state.containsKey(StateParameter.enemyBearing)){
            state.put(StateParameter.enemyDirectionX, 0.0);
            state.put(StateParameter.enemyDirectionY, 0.0);
            return;
        }

        Vector myDirection = new Vector(state.get(StateParameter.sinMyHeading), state.get(StateParameter.cosMyHeading));
        Vector enemyDirection = rotate(myDirection, -state.get(StateParameter.enemyBearing));

        state.put(StateParameter.enemyDirectionX, enemyDirection.getA());
        state.put(StateParameter.enemyDirectionY, enemyDirection.getB());
    }
}
