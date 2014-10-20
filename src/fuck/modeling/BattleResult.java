package fuck.modeling;

import robocode.BattleResults;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;

/**
 * Created by user50 on 19.10.2014.
 */
public class BattleResult extends BattleAdaptor {

    String robotName;

    Integer scoreDiff;

    public BattleResult(String robotName) {
        this.robotName = robotName;
    }

    @Override
    public void onBattleCompleted(BattleCompletedEvent event) {
        synchronized (this) {
            if (event.getIndexedResults()[0].getTeamLeaderName().equals(robotName)) {
                scoreDiff = event.getIndexedResults()[0].getScore() - event.getIndexedResults()[1].getScore();
            } else {
                scoreDiff = event.getIndexedResults()[1].getScore() - event.getIndexedResults()[0].getScore();
            }

            notifyAll();
        }
    }

    public synchronized double getScoreDiff() throws InterruptedException {
        while (this.scoreDiff == null) {
            wait(5000);
        }

        Integer scoreDiff = this.scoreDiff;
        this.scoreDiff = null;

        notifyAll();
        return scoreDiff;
    }

}
