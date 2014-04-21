package fuck;

import com.example.Vector;
import fuck.module.*;
import robocode.AdvancedRobot;
import robocode.DeathEvent;
import robocode.ScannedRobotEvent;

import static com.example.VectorAlgebra.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 9:14
 * To change this template use File | Settings | File Templates.
 */
public class Fucker extends AdvancedRobot
{

  private boolean alive = true;

  private FireModule fireModule = new SimpleFireModule();
  private GunModule gunModule = new SimpleGunModule();
  private RadarModule radarModule = new SimpleRadarModule();
  private BodyModule bodyModule = new FuckersBody();

  private Vector enemy;
  double enemyHearing;
  double enemyVelocity;

  @Override
  public void run()
  {
    setTurnRadarLeftRadians( Double.MAX_VALUE );

    while( alive )
    {
      Vector me = new Vector( getX(), getY() );
      if( enemy != null )
      {
        setAhead( bodyModule.ahead( me, enemy ) );
        setTurnLeftRadians( bodyModule.rotate( me, enemy, getHeadingRadians() ) );
        setTurnRadarLeftRadians( radarModule.angle( getRadarHeadingRadians(), me, enemy ) );
        setTurnGunLeftRadians( gunModule.angle( getGunHeadingRadians(), me, enemy, enemyHearing, enemyVelocity ) );

        State state = new State();
        state.enemy = enemy;
        state.me = me;
        if( fireModule.isFire(state) )
          fire( fireModule.power( enemy, me ) );
      }

      execute();
    }

  }

  @Override
  public void onScannedRobot( ScannedRobotEvent event )
  {
    Vector me = new Vector( getX(), getY() );
    Vector myDirection = new Vector( sin( getHeadingRadians() ), cos( getHeadingRadians() ) );
    Vector myToEnemyDirection = rotate( myDirection, -event.getBearingRadians() );
    Vector meToEnemy = prod( myToEnemyDirection, event.getDistance() );

    enemyHearing = event.getHeadingRadians();
    enemyVelocity = event.getVelocity();

    enemy = sum( meToEnemy, me );
  }

  @Override
  public void onDeath( DeathEvent event )
  {
    alive = false;
  }
}
