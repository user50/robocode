package fuck.module;

import com.example.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public interface FireModule
{

  double power(Vector enemy, Vector me);

  boolean isFire( Vector me , double gunHearing, Vector enemy, double enemyHearing, double enemyVelocity);

}
