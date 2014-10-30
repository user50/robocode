package fuck;

import robocode.AdvancedRobot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public enum Action {
    ahead {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setAhead(value);
        }

        @Override
        public double generateRandomValidValue() {
            return Math.random() * 400 - 200;
        }
    },
    rotate {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnLeftRadians(value);
        }

        @Override
        public double generateRandomValidValue() {
            return Math.random() * 2 * Math.PI - Math.PI;
        }
    },
    rotateGun {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnGunLeftRadians(value);
        }

        @Override
        public double generateRandomValidValue() {
            return Math.random() * 2 * Math.PI - Math.PI;
        }
    },
    rotateRadar {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnRadarLeftRadians(value);
        }

        @Override
        public double generateRandomValidValue() {
            return Math.random() * 2 * Math.PI - Math.PI;
        }
    },
    shotPower {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.fire(value);
        }

        @Override
        public double generateRandomValidValue() {
            return Math.random() * 2;
        }
    };

    public abstract void applyAction(AdvancedRobot robot, double value);

    public abstract double generateRandomValidValue();


    public static Map<Action, Double> generateRandomAction()
    {
        Map<Action, Double> decision = new HashMap<>();

        for (Action action : Action.values())
            decision.put(action, action.generateRandomValidValue());

        return decision;
    }
}
