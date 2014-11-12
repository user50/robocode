package brainstechnology.annealing;

public class AnnealingSimulation{

    private OptimizationProblem problem;
    private Mutator mutator;

    private int maxIteration = 1000;  // default max iteration

    public double startingTemperature = 1000; //

    public AnnealingSimulation(OptimizationProblem problem, Mutator mutator) {
        this.problem = problem;
        this.mutator = mutator;
    }

    public double[] search(){
        double[] currentState = problem.initState();
        int iteration = 1;
        double temperature = mutator.cooling(startingTemperature, iteration++);

        double currentStateEnergy = problem.evaluate(currentState);

        do {
            double[] nextState = nextState(currentState, temperature);

            double nextStateEnergy = problem.evaluate(nextState);

            if(Math.random() < probability(currentStateEnergy, nextStateEnergy, temperature)){
                currentState = nextState;
                currentStateEnergy = nextStateEnergy;
            }

            temperature = mutator.cooling(startingTemperature, iteration++);
        }while ( iteration < maxIteration );

        return currentState;
    }

    private double probability(double currentStateEnergy, double nextStateEnergy, double temperature) {
        return 1/(1+Math.exp((nextStateEnergy-currentStateEnergy)/temperature));
    }

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public double getStartingTemperature() {
        return startingTemperature;
    }

    public void setStartingTemperature(double startingTemperature) {
        this.startingTemperature = startingTemperature;
    }

    //transition
    protected double[] nextState(double state[], double temperature)
    {
        double[] nextState = new double[state.length];

        for (int i = 0; i < state.length; i++)
            nextState[i] = mutator.mutate(state[i], startingTemperature);

        return nextState;
    }
}
