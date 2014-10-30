package fuck.predictors;

import fuck.algebra.Vector;
import fuck.algebra.VectorAlgebra;
import fuck.Action;
import fuck.StateParameter;
import fuck.inductivitylearning.SingleStateParameterPredictor;

import java.util.Map;

import static fuck.StateParameter.gunCos;
import static fuck.StateParameter.gunSin;

/**
 * Created by user50 on 24.10.2014.
 */
public class GunDirectionPredictor implements SingleStateParameterPredictor {
    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        Vector gunDirection = new Vector(state.get(gunSin), state.get(gunCos));

        gunDirection = VectorAlgebra.rotate(gunDirection, action.get(Action.rotateGun));

        nextState.put(gunSin, gunDirection.getA());
        nextState.put(gunCos, gunDirection.getB());
    }
}
