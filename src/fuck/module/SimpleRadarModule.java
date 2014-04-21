package fuck.module;

import com.example.Vector;
import com.example.VectorAlgebra;

import static com.example.VectorAlgebra.getAngleBetween;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class SimpleRadarModule implements RadarModule
{
  @Override
  public double angle( double radarHearing, Vector me, Vector enemy )
  {
    Vector radarDirection = new Vector( sin( radarHearing ), cos( radarHearing )  );
    double angle = getAngleBetween( radarDirection, VectorAlgebra.diff( enemy, me ) );

    return  angle + Math.signum( angle ) * Math.toRadians( 10 );
  }
}
