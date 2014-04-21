package fuck.module;

import com.example.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public interface BodyModule
{
  public double ahead(Vector me, Vector enemy);
  double rotate(Vector me, Vector enemy, double hearing);
}
