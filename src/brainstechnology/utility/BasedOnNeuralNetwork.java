package brainstechnology.utility;

import brainstechnology.inductivitylearning.UtilityAssessor;
import fuck.StateParameter;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.util.Map;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 02.11.2014.
 */
public class BasedOnNeuralNetwork implements UtilityAssessor {

    public final static String FILE = "utility.eg";

    BasicNetwork network;

    public BasedOnNeuralNetwork(File file) {
        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(file);
    }

    @Override
    public double asses(Map<StateParameter, Double> state) {
        if (!state.containsKey(StateParameter.enemyBearing))
            return 0;

        double[] input = new double[]{
                state.get(StateParameter.velocity)/8,
                state.get(StateParameter.x)/800,
                state.get(StateParameter.y)/600,
                state.get(StateParameter.distanceToEnemy)/1000,
                sin(state.get(StateParameter.enemyBearing)),
                cos(state.get(StateParameter.enemyBearing)),
                sin(state.get(StateParameter.enemyHearing)),
                cos(state.get(StateParameter.enemyHearing)),
                state.get(StateParameter.enemyVelocity)/8,
                sin(state.get(StateParameter.hearing)),
                cos(state.get(StateParameter.hearing))
        };

        return 10 * network.compute(new BasicMLData(input)).getData(0);
    }
}
