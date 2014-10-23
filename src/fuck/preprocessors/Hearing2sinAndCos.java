package fuck.preprocessors;

import fuck.StateParameter;
import fuck.inductivitylearning.Handler;

import java.util.Map;

import static fuck.StateParameter.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 23.10.2014.
 */
public class Hearing2sinAndCos implements Handler {
    @Override
    public void process(Map<StateParameter, Double> state) {
        state.put(sinMyHeading, sin(state.get(hearing)));
        state.put(cosMyHeading, cos(state.get(hearing)));

        state.remove(hearing);
    }
}
