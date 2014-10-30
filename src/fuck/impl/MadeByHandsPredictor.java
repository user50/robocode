package fuck.impl;

import fuck.inductivitylearning.StatePredictor;
import fuck.predictors.*;


/**
 * Created by user50 on 21.10.2014.
 */
public class MadeByHandsPredictor extends StatePredictor {

    public MadeByHandsPredictor() {
        predictors.add( new VelocityPredictor() );
        predictors.add( new DirectionPredictor() );
        predictors.add( new PositionPredictor() );
        predictors.add( new GunDirectionPredictor() );
        predictors.add( new DistanceToEnemyPredictor() );
        predictors.add( new EnemyDirectionPredictor() );
    }
}
