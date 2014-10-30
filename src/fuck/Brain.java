package fuck;

import fuck.inductivitylearning.*;

import java.util.Map;

/**
 * Created by user50 on 26.10.2014.
 */
public class Brain {
    StatePredictor statePredictor;
    Optimiser optimiser;
    UtilityCalculator utilityCalculator;
    PreProcess preProcess;

    protected Brain(StatePredictor statePredictor, Optimiser optimiser, UtilityCalculator utilityCalculator, PreProcess preProcess) {
        this.statePredictor = statePredictor;
        this.optimiser = optimiser;
        this.utilityCalculator = utilityCalculator;
        this.preProcess = preProcess;
    }

    public Map<Action, Double> askAction(Map<StateParameter, Double> state)
    {
        Map<StateParameter, Double> processedState = preProcess.process(state);

        ActionAssessor actionAssessor = new ActionAssessor(statePredictor, utilityCalculator, processedState);

        return optimiser.findOptimalSolution(actionAssessor);
    }

    private static class ActionAssessor implements Function<Map<Action,Double>>{

        StatePredictor statePredictor;
        UtilityCalculator utilityCalculator;
        Map<StateParameter, Double> state;

        private ActionAssessor(StatePredictor statePredictor, UtilityCalculator utilityCalculator, Map<StateParameter, Double> state) {
            this.statePredictor = statePredictor;
            this.utilityCalculator = utilityCalculator;
            this.state = state;
        }

        @Override
        public double calculate(Map<Action, Double> actions) {
            Map<StateParameter, Double> nextState = statePredictor.nextState(state, actions);

            return utilityCalculator.assessState(nextState);
        }
    }

}
