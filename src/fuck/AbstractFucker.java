package fuck;

import algebra.Vector;
import algebra.VectorAlgebra;
import robocode.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algebra.VectorAlgebra.rotate;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 12.10.2014.
 */
public abstract class AbstractFucker extends AdvancedRobot {

    private List<Listener> listeners = new ArrayList<>();

    private boolean stop = false;

    List<Snapshot> snapshots = new ArrayList<>();

    Map<Action, Double> previousActions;
    Map<StateParameter, Double> previousState;

    ScannedRobotEvent lastScannedEnemy;

    @Override
    public void run() {
        init();
        applyActions(specifyInitActions());

        while (!stop)
        {
            applyActions(specifyActions());

            if (previousState != null && previousActions != null)
                snapshots.add(new Snapshot(previousActions, previousState, getState()));

            previousState = getState();

            execute();
        }
    }

    protected void init()
    {

    }

    private void applyActions(Map<Action, Double> actions)
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

        parameters.put(StateParameter.hearing, getHeadingRadians());
        parameters.put(StateParameter.velocity, getVelocity());
        parameters.put(StateParameter.x, getX());
        parameters.put(StateParameter.y, getY());
        parameters.put(StateParameter.gunHearing, getGunHeadingRadians());

        if (lastScannedEnemy != null)
        {
            parameters.put(StateParameter.distanceToEnemy, lastScannedEnemy.getDistance());
            parameters.put(StateParameter.enemyBearing, lastScannedEnemy.getBearingRadians());
            parameters.put(StateParameter.enemyHearing, lastScannedEnemy.getHeadingRadians());
            parameters.put(StateParameter.enemyVelocity, lastScannedEnemy.getVelocity());
        }

        parameters.put(StateParameter.width, getBattleFieldWidth());
        parameters.put(StateParameter.height, getBattleFieldHeight());

        return parameters;
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        lastScannedEnemy = event;
    }

    protected abstract Map<Action, Double> specifyActions();

    protected abstract Map<Action, Double> specifyInitActions();


    @Override
    public void onPaint(Graphics2D g) {
        if (lastScannedEnemy == null)
            return;

        Vector myDirection = new Vector(sin(getHeadingRadians()), cos(getHeadingRadians()));
        Vector enemyDirection = rotate(myDirection, -lastScannedEnemy.getBearingRadians());

        enemyDirection = VectorAlgebra.prod(enemyDirection, 1000);
        g.setColor(Color.orange);
        g.draw(new Line2D.Double(getX(),getY(),(getX() + enemyDirection.getA()), (getY() + enemyDirection.getB()) ));
    }

    @Override
    public void onRoundEnded(RoundEndedEvent event) {
        for (Listener listener : listeners)
            listener.onRoundEnded();
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        stop = true;
    }

    public void addListener(Listener listener)
    {
        listeners.add(listener);
    }
}
