package fuck;

import database.HibernateUtil;
import database.State;
import org.hibernate.Session;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;

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
    private final static String ROBOT_NAME = "fuck.SuperJeka";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        RobocodeEngine engine = new RobocodeEngine(new File("d:\\games\\robocode"));
        BattleResult battleResult = new BattleResult(ROBOT_NAME);
        engine.addBattleListener(battleResult);

//      engine.addBattleListener(new Listener() );

        BattleSpecification specification = new BattleSpecification(1, new BattlefieldSpecification(800, 600), engine.getLocalRepository("fuck.SuperJeka, froh.micro.Aversari"));

        for (int i = 0; i < 1000; i++) {
            engine.runBattle(specification);

            engine.waitTillBattleOver();

            double diff = battleResult.getScoreDiff();
            try {
                List<Snapshot> snapshots = (List<Snapshot>) InputOutputUtil.load("D:\\games\\robocode\\robots\\.data\\fuck\\SuperJeka.data\\snapshots");
                System.out.println(new File("D:\\games\\robocode\\robots\\.data\\fuck\\SuperJeka.data\\snapshots").delete());

                adapt(snapshots, diff);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(new File("D:\\games\\robocode\\robots\\.data\\fuck\\SuperJeka.data\\snapshots").delete());
            }
        }

    }

    private static void adapt(List<Snapshot> snapshots, double diff) {

    }

    private static void save(final List<Snapshot> snapshots, final double scoreDiff) {
        HibernateUtil.execute(new HibernateUtil.Update() {
            @Override
            public void execute(Session session) {
                for (Snapshot snapshot : snapshots) {
                    session.save(new State(snapshot.state, scoreDiff));
                }
            }
        });
    }

}
