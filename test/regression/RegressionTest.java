package regression;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public class RegressionTest {
    @Test
    public void testRegressLine() throws Exception {
        double x1 = 1;
        double x2 = 3;

        double y1 = 1 + 2 * x1;
        double y2 = 1 + 2 * x2;

        Regression regression = new Regression(Arrays.asList( new Constant(), new SimplePow("x", 1) ));

        Map<String, Double> input1 = new HashMap<String, Double>();
        input1.put("x",x1);

        Map<String, Double> input2 = new HashMap<String, Double>();
        input2.put("x",x2);

        double alpha = 0.05;
        for (int i = 0; i < 100; i++) {
            regression.adapt(input1, y1, alpha);
            regression.adapt(input2, y2, alpha);
        }

        double estimate1 = regression.calculate(input1);
        double estimate2 = regression.calculate(input2);

        Assert.assertTrue( Math.abs( y1 - estimate1 ) < 0.1 );
        Assert.assertTrue( Math.abs( y2 - estimate2 ) < 0.1 );
    }
}
