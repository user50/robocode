package fuck.module;

import com.example.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
public interface GunModule
{

  double angle(double gunHeading, Vector me, Vector enemy, double enemyHearing, double enemyVelocity);


}
