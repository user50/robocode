package fuck.module;

import com.example.Vector;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 26.04.14
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class MonteCarloFireModuleTest
{
  @Test
  public void testEvaluateProbability() throws Exception
  {
    Vector me = new Vector( 70,60 );
    Vector enemy = new Vector( 150*3,130*3 );
    double gunHearing = Math.PI/4;
    double enemyHearing = -Math.PI/2;
    double enemyVelocityValue = 1;


    MonteCarloFireModule module = new MonteCarloFireModule();
    module.setPower( 3 );
    module.evaluateProbability( me, gunHearing, enemy, enemyHearing, enemyVelocityValue );


  }
}
