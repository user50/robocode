package fuck.module;

import com.example.Vector;
import fuck.State;

import static com.example.VectorAlgebra.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 21.04.14
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class MonteCarloFireModule implements FireModule
{
  private double power = 2;

  private double maximumX = 1000;
  private double maximumY = 1000 ;

  private static final int SIMULATION_COUNT = 1000;
  private static final double TANK_EDGE = 36;

  @Override
  public double power( Vector enemy, Vector me )
  {
    return power;
  }

  @Override
  public boolean isFire( Vector me , double gunHearing, Vector enemy, double enemyHearing, double enemyVelocity)
  {
    double probabilityHitting = evaluateProbability( me, gunHearing, enemy, enemyHearing, enemyVelocity );

    return probabilityHitting * (4 * power + (power>1 ? 2 * (power - 1) : 0) + 3 * power ) > power * ( 1 - probabilityHitting);
  }

  double evaluateProbability( Vector me, double gunHearing, Vector enemy, double enemyHearing, double enemyVelocity )
  {
    State initialState = new State();
    initialState.me = me;
    initialState.enemy = enemy;
    initialState.enemyVelocity = prod( new Vector( sin( enemyHearing ), cos( enemyHearing ) ), enemyVelocity );

    initialState.bullet = me;
    initialState.bulletVelocity = prod( new Vector( sin( gunHearing ), cos( gunHearing ) ), 20 - power * 3 );

    double count = 0;
    for( int i=0; i<SIMULATION_COUNT; i++ )
      if( simulate( initialState ) )
        count++;

    return count/SIMULATION_COUNT;
  }

  private boolean simulate(State state)
  {

    while( true )
    {
      state = state.nextState();

      if( isTerminal( state ) )
        return true;

      if( isDeafKut(state) )
        return false;
    }

  }

  private boolean isDeafKut( State state )
  {
    return state.bullet.getA() > maximumX || state.bullet.getA() < 0 || state.bullet.getB() > maximumY || state.bullet.getB() < 0;
  }

  private boolean isTerminal( State state )
  {
    Vector currentBulletPosition = state.bullet;
    Vector previousBulletPosition = diff( currentBulletPosition, state.bulletVelocity );
    Vector enemy = state.enemy;

    return isLineIntersectRectangle( previousBulletPosition, currentBulletPosition, enemy, TANK_EDGE );
  }

  public void setPower( double power )
  {
    this.power = power;
  }
}
