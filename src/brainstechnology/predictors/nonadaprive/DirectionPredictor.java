package brainstechnology.predictors.nonadaprive;

import fuck.Action;
import fuck.StateParameter;
import algebra.Vector;
import algebra.VectorAlgebra;

import java.util.Map;

import static fuck.Action.rotate;
import static fuck.StateParameter.cosMyHeading;
import static fuck.StateParameter.sinMyHeading;

/**
 * Created by user50 on 23.10.2014.
 */
public class DirectionPredictor extends NonAdaptivePredictor {

    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        Vector direction = new Vector(state.get(sinMyHeading), state.get(cosMyHeading));
        direction = VectorAlgebra.rotate(direction, action.get(rotate));

        nextState.put(sinMyHeading, direction.getA());
        nextState.put(cosMyHeading, direction.getB());
    }
}
