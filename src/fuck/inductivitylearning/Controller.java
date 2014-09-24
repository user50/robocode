package fuck.inductivitylearning;

/**
 * Created by user50 on 24.09.2014.
 */
public class Controller {

    StatePredictor statePredictor;
    SpecificationCalculator specificationCalculator;
    UtilityCalculator utilityCalculator;

    public Action defineAction(final Environment environment)
    {
        return findOptimalAction(new Function() {
            @Override
            public double calculate(Object arguments) {
                Action act = (Action) arguments;
                return utilityCalculator.calculate(specificationCalculator.calculate(statePredictor.nextState( environment, act)));
            }
        });
    }

    private Action findOptimalAction( Function function ) {
        return null;
    }
}
