package fuck.modeling;

import fuck.InputOutputUtil;
import fuck.Snapshot;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.IBattleListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 26.04.14
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    private final static String ROBOT_NAME = "fuck.Jeka*";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        RobocodeEngine engine = new RobocodeEngine(new File("d:\\games\\robocode"));
        BattleResult battleResult = new BattleResult(ROBOT_NAME);
        engine.addBattleListener(battleResult);

//      engine.addBattleListener(new Listener() );

        BattleSpecification specification = new BattleSpecification(1, new BattlefieldSpecification(1000, 1000), engine.getLocalRepository(ROBOT_NAME+", apollokidd.ApolloKidd"));

        for (int i = 0; i < 10; i++) {
            engine.runBattle(specification);

            engine.waitTillBattleOver();

            System.out.println(battleResult.getScoreDiff());
            List<Snapshot> snapshots = (List<Snapshot>) InputOutputUtil.load("d:\\games\\robocode\\robots\\fuck\\Jeka.data\\snapshots");
        }

    }
}
