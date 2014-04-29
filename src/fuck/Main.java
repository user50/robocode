package fuck;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 26.04.14
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
  public static void main( String[] args )
  {
    RobocodeEngine engine = new RobocodeEngine( new File( "c:\\robocode" ) );
    engine.addBattleListener( new BattleAdaptor() {
      @Override
      public void onBattleCompleted( BattleCompletedEvent event )
      {
        event.getIndexedResults();
      }
    } );

    BattleSpecification specification = new BattleSpecification( 10, new BattlefieldSpecification( 1000, 1000 ), engine.getLocalRepository( "jk.precise.EnergyDome, satan.R0" )  );

    engine.runBattle( specification );

    engine.waitTillBattleOver();

  }
}
