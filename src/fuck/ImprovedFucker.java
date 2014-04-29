package fuck;

import fuck.module.*;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 26.04.14
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class ImprovedFucker extends Fucker
{

  public ImprovedFucker()
  {
    fireModule = new MonteCarloFireModule();
    gunModule = new SimpleGunModule();
    radarModule = new SimpleRadarModule();
    bodyModule = new FuckersBody();
  }
}
