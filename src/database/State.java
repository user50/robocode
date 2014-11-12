package database;

import fuck.StateParameter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 01.11.2014.
 */
@Entity
@Access(AccessType.FIELD)
public class State {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int id;

    public Double velocity;
    public Double x;
    public Double y;
    public Double distanceToEnemy;
    public Double enemyBearing;
    public Double width;
    public Double height;
    public Double enemyHearing;
    public Double enemyVelocity;
    public Double gunHearing;
    public Double hearing;
    public Double overScore;

    public State() {
    }

    public State(Map<StateParameter, Double> state, double overScore) {
        velocity = state.get(StateParameter.velocity);
        x = state.get(StateParameter.x);
        y = state.get(StateParameter.y);
        distanceToEnemy = state.get(StateParameter.distanceToEnemy);
        enemyBearing = state.get(StateParameter.enemyBearing);
        width = state.get(StateParameter.width);
        height = state.get(StateParameter.height);
        enemyHearing = state.get(StateParameter.enemyHearing);
        enemyVelocity = state.get(StateParameter.enemyVelocity);
        hearing = state.get(StateParameter.hearing);
        gunHearing = state.get(StateParameter.gunHearing);
        this.overScore = overScore;
    }

    public Map<StateParameter, Double> toMap()
    {
        Map<StateParameter, Double> state = new HashMap<>();

        state.put(StateParameter.velocity, velocity);
        state.put(StateParameter.x, x);
        state.put(StateParameter.y, y);
        state.put(StateParameter.distanceToEnemy, distanceToEnemy);
        state.put(StateParameter.enemyBearing, enemyBearing);
        state.put(StateParameter.width, width);
        state.put(StateParameter.height, height);
        state.put(StateParameter.enemyHearing, enemyHearing);
        state.put(StateParameter.enemyVelocity, enemyVelocity);
        state.put(StateParameter.gunHearing, gunHearing);
        state.put(StateParameter.hearing, hearing);

        return state;

    }
}
