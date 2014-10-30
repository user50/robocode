package fuck.preprocessors;

import fuck.StateParameter;
import fuck.inductivitylearning.Handler;

import java.util.Map;

/**
 * Created by user50 on 24.10.2014.
 */
public class GunHearing2GunDirection implements Handler {
    @Override
    public void process(Map<StateParameter, Double> state) {
        state.put(StateParameter.gunSin, Math.sin(state.get(StateParameter.gunHearing)));
        state.put(StateParameter.gunCos, Math.cos(state.get(StateParameter.gunHearing)));

        state.remove(StateParameter.gunHearing);
    }
}
