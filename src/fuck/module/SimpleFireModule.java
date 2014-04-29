package fuck.module;

import com.example.Vector;

import static com.example.VectorAlgebra.diff;
import static com.example.VectorAlgebra.length;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFireModule implements FireModule
{
  private double fireRadius = 510;

  @Override
  public double power( Vector enemy, Vector me )
  {
    return 2 + (fireRadius - length( diff( enemy, me ) ))/fireRadius;
  }

  @Override
  public boolean isFire( Vector me , double gunHearing, Vector enemy, double enemyHearing, double enemyVelocity)
  {
    return enemy != null && length( diff( enemy, me ) ) < fireRadius;
  }
}
