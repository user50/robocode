package fuck.module;

import com.example.Vector;
import com.example.VectorAlgebra;

import static com.example.VectorAlgebra.*;
import static com.example.VectorAlgebra.diff;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class SimpleGunModule implements GunModule
{
  @Override
  public double angle(double gunHeading, Vector me, Vector enemy, double enemyHearing, double enemyVelocityValue)
  {
    double distanceToEnemy = length( diff( enemy, me ) );

    Vector gunDirection = new Vector( sin( gunHeading ), cos( gunHeading )  );
    Vector enemyDirection = new Vector( sin( enemyHearing ), cos( enemyHearing )  );

    Vector predictedEnemyPosition = predict( enemy, prod( enemyDirection, enemyVelocityValue ), getPredictionTurns( distanceToEnemy ) );

    return getAngleBetween( gunDirection, VectorAlgebra.diff( predictedEnemyPosition, me ) );
  }

  private Vector predict(Vector enemy, Vector enemyVelocity, int turnsCount )
  {
    return sum(enemy, prod( enemyVelocity, turnsCount ));
  }

  private int getPredictionTurns(double distanceToEnemy)
  {
    return (int) distanceToEnemy/20;
  }
}
