package fuck;

import fuck.module.*;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 26.04.14
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFucker extends Fucker
{
  public SimpleFucker()
  {
    fireModule = new SimpleFireModule();
    gunModule = new SimpleGunModule();
    radarModule = new SimpleRadarModule();
    bodyModule = new FuckersBody();
  }
}
