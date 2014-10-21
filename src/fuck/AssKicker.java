package fuck;

import fuck.inductivitylearning.Function;
import fuck.inductivitylearning.Optimiser;
import fuck.inductivitylearning.StatePredictor;
import fuck.inductivitylearning.UtilityCalculator;

import java.util.Map;

/**
 * Created by user50 on 21.10.2014.
 */
public abstract class AssKicker extends AbstractFucker {

    StatePredictor statePredictor;
    Optimiser optimiser;
    UtilityCalculator utilityCalculator;

    protected AssKicker(StatePredictor statePredictor, Optimiser optimiser, UtilityCalculator utilityCalculator) {
        this.statePredictor = statePredictor;
        this.optimiser = optimiser;
        this.utilityCalculator = utilityCalculator;
    }

    @Override
    protected Map<Action, Double> makeAction() {

        final Map<StateParameter, Double> state = getState();
        Function<Map<Action, Double>> function = new Function<Map<Action, Double>>()
        {

            @Override
            public double calculate(Map<Action, Double> action) {
                return utilityCalculator.calculate(statePredictor.nextState(state, action));
            }
        };

        return optimiser.solve(function);
    }
}
