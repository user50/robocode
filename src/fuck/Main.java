package fuck;

import regression.Regression;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            Regression regression = (Regression) InputOutputUtil.load("predictors/velocityPredictor");
            adapt(regression, snapshots);
            InputOutputUtil.save(regression,new File("predictors/velocityPredictor"));

        }

    }

    private static void adapt(Regression regression, List<Snapshot> snapshots) {

        for (Snapshot snapshot : snapshots) {
            double velocity = snapshot.nextState.get(StateParameter.velocity);
            Map<String,Double> input = new HashMap<>();

            input.put(Action.ahead.name(), snapshot.actions.get(Action.ahead));
            input.put(StateParameter.velocity.name(), snapshot.state.get(StateParameter.velocity));

            regression.adapt(input, velocity, 0.001);
        }

    }

}
