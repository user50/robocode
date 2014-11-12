package fuck;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 01.11.2014.
 */
public class ActionUtil {

    public static double[] toArray(Map<Action, Double> actions)
    {
        double[] array = new double[Action.values().length];

        int i=0;
        for (Action action : Action.values())
            array[i++] = actions.get(action);

        return array;
    }

    public static Map<Action, Double> toMap(double[] actionArray)
    {
        Map<Action, Double> actions = new HashMap<>();

        int i = 0;
        for (Action action : Action.values())
            actions.put(action, actionArray[i++]);

        return actions;
    }



}
