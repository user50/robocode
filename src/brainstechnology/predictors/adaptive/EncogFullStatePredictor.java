package brainstechnology.predictors.adaptive;

import fuck.Action;
import fuck.StateParameter;
import fuck.SuperJeka;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.Map;

import static fuck.StateParameter.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 08.11.2014.
 */
public class EncogFullStatePredictor extends EncogPredictor {

    public EncogFullStatePredictor(SuperJeka superJeka) {
        super(superJeka);
    }

    @Override
    protected BasicNetwork createNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 22));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 30));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 3));
        network.addLayer(new BasicLayer(null, false, 15));
        network.getStructure().finalizeStructure();
        network.reset();

        return network;
    }

    @Override
    protected void putPrediction(MLData output, Map<StateParameter, Double> nextState) {
        int i = 0;

        for (StateParameter stateParameter : StateParameter.values())
            if ( stateParameter != width && stateParameter != height)
                nextState.put(stateParameter, output.getData(i++));

    }

    @Override
    protected double[] formInput(Map<StateParameter, Double> state, Map<Action, Double> action) {
        return new double[]{
                state.get(velocity)/8,
                state.get(x)/1000,
                state.get(y)/1000,
                state.containsKey(distanceToEnemy) ? state.get(distanceToEnemy)/1000 : 0,
                state.containsKey(enemyBearing) ? state.get(enemyBearing) : 0,
                state.containsKey(enemyHearing) ? state.get(enemyHearing) : 0,
                state.containsKey(enemyVelocity) ? state.get(enemyVelocity)/8 : 0,
                state.get(gunHearing),
                state.get(hearing),

                state.get(sinMyHeading),
                state.get(cosMyHeading),
                state.get(gunSin),
                state.get(gunCos),
                state.containsKey(enemyDirectionX) ? state.get(enemyDirectionX) : 0,
                state.containsKey(enemyDirectionY) ? state.get(enemyDirectionY) : 0,

                (state.get(width) - state.get(x))/1000,
                (state.get(height) - state.get(y))/1000,

                sin(action.get(Action.rotate)),
                cos(action.get(Action.rotate)),
                sin(action.get(Action.rotateGun)),
                cos(action.get(Action.rotateGun)),
                action.get(Action.ahead)/200,
        };
    }

    @Override
    protected double[] formOutput(Map<StateParameter, Double> nextState) {
        return new double[]{
                nextState.get(velocity),
                nextState.get(x),
                nextState.get(y),
                nextState.containsKey(distanceToEnemy) ? nextState.get(distanceToEnemy) : 0,
                nextState.containsKey(enemyBearing) ? nextState.get(enemyBearing) : 0,
                nextState.containsKey(enemyHearing) ? nextState.get(enemyHearing) : 0,
                nextState.containsKey(enemyVelocity) ? nextState.get(enemyVelocity) : 0,
                nextState.get(gunHearing),
                nextState.get(hearing),

                nextState.get(sinMyHeading),
                nextState.get(cosMyHeading),
                nextState.get(gunSin),
                nextState.get(gunCos),
                nextState.containsKey(enemyDirectionX) ? nextState.get(enemyDirectionX) : 0,
                nextState.containsKey(enemyDirectionY) ? nextState.get(enemyDirectionY) : 0,
        };
    }

    @Override
    protected void displayError(double error) {

    }

    @Override
    protected String getFileName() {
        return "EncogFullStatePredictor.eg";
    }
}
