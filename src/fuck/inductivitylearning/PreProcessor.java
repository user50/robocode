package fuck.inductivitylearning;

import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 22.10.2014.
 */
public interface PreProcessor {

    void process(Map<StateParameter, Double> state);

}
