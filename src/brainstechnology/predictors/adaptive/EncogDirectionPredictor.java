package brainstechnology.predictors.adaptive;

import fuck.Action;
import fuck.StateParameter;
import fuck.SuperJeka;
import algebra.Vector;
import algebra.VectorAlgebra;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.Map;

public class EncogDirectionPredictor extends EncogPredictor {

    public EncogDirectionPredictor() {
        super();
    }

    public EncogDirectionPredictor(SuperJeka superJeka) {
        super(superJeka);
    }

    @Override
    protected BasicNetwork createNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 5));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 20));
        network.addLayer(new BasicLayer(null, false, 2));
        network.getStructure().finalizeStructure();
        network.reset();

        return network;
    }

    @Override
    protected void putPrediction(MLData output, Map<StateParameter, Double> nextState) {
        double length = VectorAlgebra.length(new Vector(output.getData(0), output.getData(1) ));

        nextState.put(StateParameter.sinMyHeading, output.getData(0) / length );
        nextState.put(StateParameter.cosMyHeading, output.getData(1) / length );
    }


    protected double[] formInput(Map<StateParameter, Double> state, Map<Action, Double> action)
    {
        return new double[]{
                state.get(StateParameter.sinMyHeading),
                state.get(StateParameter.cosMyHeading),
                state.get(StateParameter.velocity)/8,
                Math.sin(action.get(Action.rotate)),
                Math.cos(action.get(Action.rotate))
        };
    }

    @Override
    protected double[] formOutput(Map<StateParameter, Double> nextState) {
        return new double[]{nextState.get(StateParameter.sinMyHeading), nextState.get(StateParameter.cosMyHeading)};
    }

    @Override
    protected void displayError(double error) {

    }

    @Override
    protected String getFileName() {
        return "AdaptiveDirectionPredictor.eg";
    }
}
