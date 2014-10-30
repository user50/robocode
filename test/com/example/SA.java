package com.example;

import net.sourceforge.jannealer.AnnealingScheme;
import net.sourceforge.jannealer.ObjectiveFunction;
import org.junit.*;
import org.junit.Test;

/**
 * Created by user50 on 25.10.2014.
 */
public class SA {

    @Test
    public void testName() throws Exception {
        AnnealingScheme scheme = new AnnealingScheme();


        ObjectiveFunction function = new ObjectiveFunction() {
            @Override
            public int getNdim() {
                return 1;
            }

            @Override
            public double distance(double[] doubles) {
                return Math.pow( doubles[0] -3.3, 2 );
            }
        };

        scheme.setFunction(function);

        scheme.anneal();
    }
}
