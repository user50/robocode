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

/**
 * Created by user50 on 08.11.2014.
 */
public class EncogGunDirectionPredictor extends EncogPredictor{

    public EncogGunDirectionPredictor(SuperJeka superJeka) {
        super(superJeka);
    }

    @Override
    protected BasicNetwork createNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 7));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 20));
        network.addLayer(new BasicLayer(null, false, 2));
        network.getStructure().finalizeStructure();
        network.reset();

        return network;
    }

    @Override
    protected void putPrediction(MLData output, Map<StateParameter, Double> nextState) {
        double length = VectorAlgebra.length(new Vector(output.getData(0), output.getData(1)));

        nextState.put(StateParameter.gunSin, output.getData(0) / length );
        nextState.put(StateParameter.gunCos, output.getData(1) / length );
    }

    @Override
    protected double[] formInput(Map<StateParameter, Double> state, Map<Action, Double> action) {
        return new double[]{
                state.get(StateParameter.gunSin),
                state.get(StateParameter.gunCos),
                state.get(StateParameter.velocity)/8,
                Math.sin(action.get(Action.rotate)),
                Math.cos(action.get(Action.rotate)),
                Math.sin(action.get(Action.rotateGun)),
                Math.cos(action.get(Action.rotateGun)),
        };
    }

    @Override
    protected double[] formOutput(Map<StateParameter, Double> nextState) {
        return new double[]{nextState.get(StateParameter.gunSin), nextState.get(StateParameter.gunCos)};
    }

    @Override
    protected void displayError(double error) {
    }

    @Override
    protected String getFileName() {
        return "EncogGunDirectionPredictor.eg";
    }
}
