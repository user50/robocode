package brainstechnology.preprocessors;

import brainstechnology.inductivitylearning.Handler;
import fuck.StateParameter;

import java.util.Map;

import static fuck.StateParameter.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 23.10.2014.
 */
public class Hearing2Direction implements Handler {
    @Override
    public void process(Map<StateParameter, Double> state) {
        state.put(sinMyHeading, sin(state.get(hearing)));
        state.put(cosMyHeading, cos(state.get(hearing)));
    }
}
