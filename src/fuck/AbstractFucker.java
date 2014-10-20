package fuck;

import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.RobotDeathEvent;
import robocode.RoundEndedEvent;
import sun.misc.IOUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 12.10.2014.
 */
public abstract class AbstractFucker extends AdvancedRobot {

    private boolean stop = false;

    List<Snapshot> snapshots = new ArrayList<>();

    Map<Action, Double> previousActions;
    Map<StateParameter, Double> previousState;

    @Override
    public void run() {
        applyAction(initAction());

        while (!stop)
        {
            applyAction(makeAction());

            if (previousState != null && previousActions != null)
                snapshots.add(new Snapshot(previousActions, previousState, getState()));

            previousState = getState();

            execute();
        }
    }

    private void applyAction(Map<Action, Double> actions)
    {
        for (Action action : actions.keySet())
            if (actions.containsKey(action))
                action.applyAction(this, actions.get(action));

        for (Action action : Action.values()) {
            if (!actions.containsKey(action))
                actions.put(action, 0.0);
        }

        previousActions = actions;
    }

    protected Map<StateParameter, Double> getState()
    {
        Map<StateParameter, Double> parameters = new HashMap<StateParameter, Double>();

        parameters.put(StateParameter.cosMyHeading, cos(getHeadingRadians()));
        parameters.put(StateParameter.sinMyHeading, sin(getHeadingRadians()));
        parameters.put(StateParameter.velocity, getVelocity());
        parameters.put(StateParameter.x, getX());
        parameters.put(StateParameter.y, getY());

        return parameters;
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
        InputOutputUtil.save((Serializable) snapshots, getDataFile("snapshots"));
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        stop = true;
    }

    protected abstract Map<Action, Double> makeAction();

    protected abstract Map<Action, Double> initAction();
}
