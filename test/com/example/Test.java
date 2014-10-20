package com.example;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;

/**
 * Created by user50 on 28.09.2014.
 */
public class Test {

    @org.junit.Test
    public void testName() throws Exception {
        // create new perceptron network
        NeuralNetwork neuralNetwork = new MultiLayerPerceptron(2, 4,  1);
// create training set
        DataSet trainingSet =
                new DataSet(2, 1);
// add training data to training set (logical OR function)
        trainingSet. addRow (new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet. addRow (new DataSetRow (new double[]{0, 1},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 0},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 1},
                new double[]{1}));
// learn the training set
        neuralNetwork.learn(trainingSet);
// save the trained network into file
        neuralNetwork.save("or_perceptron.nnet");

    }
}
