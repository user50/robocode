package fuck;

import robocode.AdvancedRobot;

/**
 * Created by user50 on 12.10.2014.
 */
public enum Action {
    ahead {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setAhead(value);
        }
    },
    rotate {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnLeftRadians(value);
        }
    },
    rotateGun {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnGunLeftRadians(value);
        }
    },
    rotateRadar {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.setTurnRadarLeftRadians(value);
        }
    },
    shotPower {
        @Override
        public void applyAction(AdvancedRobot robot, double value) {
            robot.fire(value);
        }
    };

    public abstract void applyAction(AdvancedRobot robot, double value);
}
