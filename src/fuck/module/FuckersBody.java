package fuck.module;

import com.example.Vector;
import com.example.VectorAlgebra;

import static com.example.VectorAlgebra.*;
import static java.lang.Math.*;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class FuckersBody implements BodyModule
{
  @Override
  public double ahead( Vector me, Vector enemy )
  {
    double distanceToEnemy = length( diff( enemy, me ) );

    if( distanceToEnemy > 500 || distanceToEnemy < 80)
    {
      return  -200;
    }
    else
    {
      return  200 - 400 * random();
    }
  }

  @Override
  public double rotate(Vector me, Vector enemy, double hearing)
  {
    Vector meToEnemy = diff(enemy, me);
    Vector normalToEnemy = VectorAlgebra.normal( meToEnemy );
    Vector myDirection = new Vector( sin( hearing ), cos( hearing ) );

    Vector nextDirection = prod( normalToEnemy, signum( scalarProd( normalToEnemy, myDirection ) ) );

    double distanceToEnemy = length( meToEnemy );
    if( distanceToEnemy > 500 )
      nextDirection = sum( sum( inverse( meToEnemy ), myDirection ), prod( normalToEnemy, (random() - 0.5 )*2 ));
    else if( distanceToEnemy < 80 )
      nextDirection = sum( sum( meToEnemy, myDirection ), prod( normalToEnemy, ( random() - 0.5 )*2 ) );

    return getAngleBetween( myDirection, nextDirection );
  }
}
