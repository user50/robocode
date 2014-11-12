package brainstechnology.predictors.adaptive;

import brainstechnology.inductivitylearning.SingleStateParameterPredictor;
import fuck.Action;
import fuck.Listener;
import fuck.StateParameter;
import fuck.SuperJeka;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.persist.EncogDirectoryPersistence;
import robocode.RobocodeFileOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by user50 on 08.11.2014.
 */
public abstract class EncogPredictor implements SingleStateParameterPredictor {

    protected BasicNetwork network;

    ExponentialAverageError averageError = new ExponentialAverageError();

    public EncogPredictor() {
        network = createNetwork();
    }

    public EncogPredictor(SuperJeka superJeka) {

        final File file = superJeka.getDataFile(getFileName());

        try {
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(file);
        }
        catch (Exception e)
        {
            network = createNetwork();
        }

        superJeka.addListener(new Listener() {
            @Override
            public void onRoundEnded() {

                try (RobocodeFileOutputStream output = new RobocodeFileOutputStream(file)) {

                    EncogDirectoryPersistence.saveObject(output, network);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void adapt(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        double[] input = formInput(state, action);
        double[] output = formOutput(nextState);

        BasicMLDataSet dataSet = new BasicMLDataSet(new double[][]{input}, new double[][]{output});

        MLTrain train =  new Backpropagation(network, dataSet, 0.1, 0);

        train.iteration(1);

        averageError.mean(train.getError());

        displayError(averageError.meanError);
    }

    @Override
    public void calculate(Map<StateParameter, Double> state, Map<Action, Double> action, Map<StateParameter, Double> nextState) {
        double[] input = formInput(state, action);
        MLData output = network.compute(new BasicMLData(input));
        putPrediction(output, nextState);
    }

    protected abstract BasicNetwork createNetwork();

    protected abstract void putPrediction(MLData output, Map<StateParameter, Double> nextState);

    protected abstract double[] formInput(Map<StateParameter, Double> state, Map<Action, Double> action);

    protected abstract double[] formOutput(Map<StateParameter, Double> nextState);

    protected abstract void displayError(double error);

    protected abstract String getFileName();

    private class ExponentialAverageError
    {
        public double meanError;
        public double alpha = 0.1;

        public void mean(double error)
        {
            meanError = meanError + alpha * (error - meanError);
        }
    }
}
