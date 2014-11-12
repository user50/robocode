package com.example;

import brainstechnology.annealing.AnnealingSimulation;
import brainstechnology.annealing.CauchyMutator;
import brainstechnology.annealing.OptimizationProblem;
import org.junit.Test;

/**
 * Created by user50 on 01.11.2014.
 */
public class MySATest {

    @Test
    public void testName() throws Exception {
        AnnealingSimulation simulation = new AnnealingSimulation(new SimpleProblem(), new CauchyMutator());
        simulation.setStartingTemperature(1);


        simulation.search();
    }

    public static class SimpleProblem implements OptimizationProblem
    {

        @Override
        public double[] initState() {
            return new double[]{0.0, 0.0};
        }

        @Override
        public double evaluate(double[] arguments) {
            return Math.pow( arguments[0] - 4, 2 ) + Math.pow( arguments[1] - 4, 2 );
        }
    }
}
