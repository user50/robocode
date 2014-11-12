package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;
import algebra.Vector;

import java.util.Map;

import static fuck.StateParameter.*;
import static algebra.VectorAlgebra.scalarProd;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 08.11.2014.
 */
public class KeepFiringLineTowardsEnemySpeed implements UtilityAssessor {
    @Override
    public double asses(Map<StateParameter, Double> state) {

        if (!state.containsKey(enemyDirectionX) || !state.containsKey(enemyHearing) )
            return 0;

        Vector directionToEnemy = new Vector(state.get(enemyDirectionX), state.get(enemyDirectionY));
        Vector enemyDirection = new Vector(sin(state.get(enemyHearing)), cos(state.get(enemyHearing)));
        Vector myDirection = new Vector(state.get(sinMyHeading), state.get(cosMyHeading));

        return 100 * abs(scalarProd(directionToEnemy, enemyDirection)) - 0 * abs(scalarProd(myDirection, enemyDirection)) ;
    }
}
