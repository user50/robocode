package brainstechnology.predictors.adaptive;

import fuck.Action;
import fuck.StateParameter;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.Map;

import static fuck.StateParameter.*;

/**
 * Created by user50 on 08.11.2014.
 */
public class EncogVelocityPredictor extends EncogPredictor{

    private final static int SCALE = 600;

    @Override
    protected BasicNetwork createNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 7));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 20));
        network.addLayer(new BasicLayer(null, false, 1));
        network.getStructure().finalizeStructure();
        network.reset();

        return network;
    }

    @Override
    protected void putPrediction(MLData output, Map<StateParameter, Double> nextState) {
        nextState.put(StateParameter.velocity, output.getData(0));
    }

    @Override
    protected double[] formInput(Map<StateParameter, Double> state, Map<Action, Double> action) {
        return new double[]{
                action.get(Action.ahead)/200,
                state.get(StateParameter.velocity)/8,
                state.containsKey(distanceToEnemy) ? state.get(distanceToEnemy)/SCALE : 1,
                state.get(x)/SCALE,
                state.get(y)/SCALE,
                (state.get(width) - state.get(x))/SCALE,
                (state.get(height) - state.get(y))/SCALE,
        };
    }

    @Override
    protected double[] formOutput(Map<StateParameter, Double> nextState) {
        return new double[]{nextState.get(StateParameter.velocity)};
    }

    @Override
    protected void displayError(double error) {
        System.out.println(error);
    }

    @Override
    protected String getFileName() {
        return "EncogVelocityPredictor.eg";
    }
}
