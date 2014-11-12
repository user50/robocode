package brainstechnology;

import brainstechnology.inductivitylearning.Optimiser;
import brainstechnology.inductivitylearning.PreProcess;
import brainstechnology.inductivitylearning.StatePredictor;
import brainstechnology.inductivitylearning.UtilityFunction;
import fuck.Action;
import fuck.StateParameter;

import java.util.Map;

/**
 * Created by user50 on 26.10.2014.
 */
public class Brain {
    StatePredictor statePredictor;
    Optimiser optimiser;
    UtilityFunction utilityFunction;
    PreProcess preProcess;

    Map<Action, Double> action;
    Map<StateParameter, Double> state;

    public Brain(StatePredictor statePredictor, Optimiser optimiser, UtilityFunction utilityFunction, PreProcess preProcess) {
        this.statePredictor = statePredictor;
        this.optimiser = optimiser;
        this.utilityFunction = utilityFunction;
        this.preProcess = preProcess;
    }

    public Map<Action, Double> askAction(Map<StateParameter, Double> state)
    {
        Map<StateParameter, Double> processedState = preProcess.process(state);
        ActionAssessor actionAssessor = new ActionAssessor(statePredictor, utilityFunction, processedState);

        if (this.state != null && this.action != null )
            statePredictor.adapt(this.state, this.action, state);

        Map<Action, Double> action = optimiser.findOptimalSolution(actionAssessor);

        remember(state, action);

        return action;
    }

    private void remember(Map<StateParameter, Double> state, Map<Action, Double> action) {
        this.state = state;
        this.action = action;
    }


}
