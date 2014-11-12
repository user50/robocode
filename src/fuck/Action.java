package fuck;

import robocode.AdvancedRobot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public enum Action {
    ahead(-200, 200) {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setAhead(value);
        }
    },
    rotate(-Math.PI * 30 / 180, Math.PI * 30 / 180) {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnLeftRadians(value);
        }
    },
    rotateGun(-Math.PI * 20 / 180, Math.PI * 20 / 180) {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnGunLeftRadians(value);
        }
    },
    rotateRadar(-Math.PI * 45 / 180, Math.PI * 45 / 180) {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnRadarLeftRadians(value);
        }

    },
    shotPower(0, 2) {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.fire(value);
        }
    };

    private double min;
    private double max;

    Action(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public abstract void applyAction(AdvancedRobot robot, double value);

    public double generateRandomValidValue()
    {
        return Math.random() * ( getMax() - getMin() ) - getMax();
    }

    public static void normalise(Map<Action, Double> actions)
    {
        for (Action action : actions.keySet())
            actions.put( action, actions.get(action)/ (action.getMax() - action.getMin()) );

    }

    public static void denormalise(Map<Action, Double> actions)
    {
        for (Action action : actions.keySet())
            actions.put( action, actions.get(action) * (action.getMax()) );

    }

    public static Map<Action, Double> generateRandomAction()
    {
        Map<Action, Double> decision = new HashMap<>();

        for (Action action : Action.values())
            decision.put(action, action.generateRandomValidValue());

        return decision;
    }
}
