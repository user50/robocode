package fuck.predictors;

import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.Action;
import fuck.StateParameter;
import fuck.inductivitylearning.SingleStateParameterPredictor;

import java.util.Map;

import static fuck.Action.rotate;
import static fuck.StateParameter.cosMyHeading;
import static fuck.StateParameter.sinMyHeading;

/**
 * Created by user50 on 23.10.2014.
 */
public class DirectionPredictor implements SingleStateParameterPredictor {

    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        Vector direction = new Vector(state.get(sinMyHeading), state.get(cosMyHeading));
        direction = VectorAlgebra.rotate(direction, action.get(rotate));

        nextState.put(sinMyHeading, direction.getA());
        nextState.put(cosMyHeading, direction.getB());
    }
}
