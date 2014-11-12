package com.example;

import database.HibernateUtil;
import database.State;
import fuck.StateParameter;
import brainstechnology.inductivitylearning.PreProcess;
import brainstechnology.preprocessors.DirectionToEnemy;
import brainstechnology.preprocessors.GunHearing2GunDirection;
import brainstechnology.preprocessors.Hearing2Direction;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.persist.EncogDirectoryPersistence;
import org.hibernate.Session;

import java.io.File;
import java.util.List;
import java.util.Map;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by user50 on 28.09.2014.
 */
public class Test {

    @org.junit.Test
    public void testName() throws Exception {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 11));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 10));
        network.addLayer(new BasicLayer(null, false, 1));
        network.getStructure().finalizeStructure();
        network.reset();

        MLTrain train =  new QuickPropagation(network, creteTrainingSet(getStates()));

        train.iteration(1000);

        EncogDirectoryPersistence.saveObject(new File("network/utility.eg"), network);

    }


    public static BasicMLDataSet creteTrainingSet( List<State> states )
    {

        double[][] input = new double[states.size()][11];
        double[][] output = new double[states.size()][1];

        int i = 0;
        for (State state : states) {
            Pair pair = createRow(state);
            input[i] = pair.input;
            output[i++] = pair.output;
        }

        return new BasicMLDataSet(input, output);
    }

    private static Pair createRow(State state)
    {
        double overScore = state.overScore;

        Map<StateParameter, Double> stateParameters = state.toMap();
        getPreProcess().process(stateParameters);

        return new Pair(new double[]{
                state.velocity/8,
                state.x/800,
                state.y/600,
                state.distanceToEnemy/1000,
                sin(state.enemyBearing),
                cos(state.enemyBearing),
                sin(state.enemyHearing),
                cos(state.enemyHearing),
                state.enemyVelocity/8,
                sin(state.hearing),
                cos(state.hearing),

        }, new double[]{overScore});
    }

    private static List<State> getStates()
    {
        return HibernateUtil.execute(new HibernateUtil.Query() {
            @Override
            public List<State> execute(Session session) {
                return session.createQuery("SELECT state FROM database.State as state WHERE state.distanceToEnemy IS NOT NULL ORDER BY RAND()").list();
            }
        });
    }

    private static class Pair
    {
        public double[] input;
        public double[] output;

        private Pair(double[] input, double[] output) {
            this.input = input;
            this.output = output;
        }
    }

    private static PreProcess getPreProcess()
    {
        return new PreProcess()
                .with(new Hearing2Direction())
                .with(new GunHearing2GunDirection())
                .with(new DirectionToEnemy());
    }

}
