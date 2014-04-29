package fuck;

import com.example.Vector;

import static com.example.VectorAlgebra.*;
import static java.lang.Math.random;

public class State
{
  public Vector me;

  public Vector enemy;
  public Vector enemyVelocity;
  public double enemyAcceleration;
  public boolean isEnemyAccelerating;
  public double enemyRotationAngle;

  public Vector bullet;
  public Vector bulletVelocity;

  public State nextState()
  {
    State nextState = new State();
    nextState.enemyRotationAngle = predictEnemyRotationAngle();
    nextState.isEnemyAccelerating = predictAccelerationDirection();

    Vector rotatedEnemyVelocity = rotate(enemyVelocity, nextState.enemyRotationAngle);
    nextState.enemyAcceleration = calculateAcceleration(length( enemyVelocity ), isEnemyAccelerating);
    nextState.enemyVelocity = sum( rotatedEnemyVelocity, prod( prod( rotatedEnemyVelocity, 1/length( rotatedEnemyVelocity ) ), nextState.enemyAcceleration ) );
    nextState.enemy = sum( enemy, nextState.enemyVelocity );

    nextState.bullet = sum( bullet, bulletVelocity );
    nextState.bulletVelocity = bulletVelocity;

    return nextState;
  }

  private double calculateAcceleration(double velocity, boolean accelerating)
  {
    if( velocity >= 7 && accelerating )
      return 8 - velocity;
    else if( velocity >= 2 && !accelerating )
      return -2;
    else if( velocity < 2 && !accelerating)
      return -velocity/2 - 1;
    else
      return 1;

  }

  private double predictEnemyRotationAngle()
  {
    return 2*( random() - 0.5 ) * (10 - 0.75 * length( enemyVelocity ));
  }

  private boolean predictAccelerationDirection()
  {
    return random() > 0.5;
  }
}
