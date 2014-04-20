package fuck;

import com.example.Vector;
import com.example.VectorAlgebra;
import robocode.AdvancedRobot;
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
  private Vector enemy;

  @Override
  public void run()
  {

    setTurnRadarLeftRadians( Double.MAX_VALUE );

    while( true )
    {
      if( enemy != null )
      {
        turnRadar();
        turnGun();
        turnBody();
      }

      setAhead( 100 );

      fire( 2 );

      execute();
    }

  }

  private void turnBody()
  {
    Vector me = new Vector( getX(), getY() );
    Vector meToEnemy = VectorAlgebra.diff(enemy, me);
  }

  private void turnGun()
  {
    Vector gunDirection = new Vector( sin( getGunHeadingRadians() ), cos( getGunHeadingRadians() )  );

    Vector me = new Vector( getX(), getY() );
    double angle = getAngleBetween( gunDirection, VectorAlgebra.diff(enemy, me) );

    setTurnGunLeftRadians( angle );
  }

  private void turnRadar()
  {
    Vector radarDirection = new Vector( sin( getRadarHeadingRadians() ), cos( getRadarHeadingRadians() )  );
    Vector me = new Vector( getX(), getY() );
    double angle = getAngleBetween( radarDirection, VectorAlgebra.diff(enemy, me) );

    setTurnRadarLeftRadians( angle + Math.signum( angle ) * Math.toRadians( 10 ) );
  }

  @Override
  public void onScannedRobot( ScannedRobotEvent event )
  {
    Vector me = new Vector( getX(), getY() );
    Vector myDirection = new Vector( sin( getHeadingRadians() ), cos( getHeadingRadians() ) );
    Vector myToEnemyDirection = rotate( myDirection, -event.getBearingRadians() );
    Vector meToEnemy = prod( myToEnemyDirection, event.getDistance() );

    enemy = sum( meToEnemy, me );
  }
}
