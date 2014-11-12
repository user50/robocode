package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;
import algebra.Vector;
import algebra.VectorAlgebra;

import java.util.Map;

import static fuck.StateParameter.*;

/**
 * Created by user50 on 02.11.2014.
 */
public class KeepGunDirectionToEnemy implements UtilityAssessor {
    @Override
    public double asses(Map<StateParameter, Double> state) {
        Vector directionToEnemy = new Vector(state.get(enemyDirectionX), state.get(enemyDirectionY));
        Vector gunDirection = new Vector(state.get(gunSin), state.get(gunCos));

        return VectorAlgebra.scalarProd(directionToEnemy, gunDirection) * 700;
    }
}
