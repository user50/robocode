package fuck.predictors;

import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.Action;
import fuck.StateParameter;
import fuck.inductivitylearning.SingleStateParameterPredictor;

import java.util.Map;

import static fuck.StateParameter.*;
import static fuck.StateParameter.cosMyHeading;

/**
 * Created by user50 on 23.10.2014.
 */
public class PositionPredictor implements SingleStateParameterPredictor {
    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {

        double velocity = nextState.get(StateParameter.velocity);
        Vector direction = new Vector(nextState.get(sinMyHeading), nextState.get(cosMyHeading));

        Vector directedVelocity = VectorAlgebra.prod(direction, velocity);
        Vector coordinates = VectorAlgebra.sum(new Vector(state.get(x), state.get(y)), directedVelocity);

        nextState.put(x, coordinates.getA());
        nextState.put(y, coordinates.getB());
    }
}
