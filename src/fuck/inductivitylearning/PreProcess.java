package fuck.inductivitylearning;

import fuck.StateParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user50 on 22.10.2014.
 */
public class PreProcess {
    List<Handler> handlers = new ArrayList<>();

    public Map<StateParameter, Double> process(Map<StateParameter, Double> state){
        for (Handler handler : handlers)
            handler.process(state);

        return state;
    }

    public PreProcess with(Handler handler)
    {
        handlers.add(handler);

        return this;
    }
}
