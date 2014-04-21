package fuck.module;

import com.example.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public interface RadarModule
{

  double angle(double radarHearing, Vector me, Vector enemy);

}
