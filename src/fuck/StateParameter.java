package fuck;

import fuck.regression.RegressionBuilder;
import fuck.regression.SinMyHeadingRegressionBuilder;
import regression.Regression;

/**
 * Created by user50 on 12.10.2014.
 */
public enum StateParameter {

    /* primary */
    velocity, x, y, distanceToEnemy, enemyBearing, width, height, enemyHearing, enemyVelocity, gunHearing, hearing,

    /* secondary */
    sinMyHeading, cosMyHeading, gunSin, gunCos, enemyDirectionX, enemyDirectionY

}
