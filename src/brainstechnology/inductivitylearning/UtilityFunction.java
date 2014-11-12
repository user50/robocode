package brainstechnology.inductivitylearning;

import fuck.StateParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user50 on 02.11.2014.
 */
public class UtilityFunction {

    List<UtilityAssessor> assessors = new ArrayList<>();

    public UtilityFunction() {
    }

    public UtilityFunction(List<UtilityAssessor> assessors) {
        this.assessors = assessors;
    }

    public double compute(Map<StateParameter, Double> state)
    {
        double utility = 0;
        for (UtilityAssessor assessor : assessors)
            utility += assessor.asses(state);

        return utility;
    }

    public void add(UtilityAssessor assessor)
    {
        assessors.add(assessor);
    }

}
