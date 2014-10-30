package regression;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user50 on 26.10.2014.
 */
public class FuckingAss {

    @Test
    public void testName() throws Exception {
        Regression regression = new Regression(Arrays.asList(new Constant(), new SimplePow("x", 1), new SimplePow("x", 2))   );

        Map<String,Double> input = new HashMap<>();
        input.put("x", 12.0);
        double y = 22;

        regression.adapt(input, y, 0.01);
        regression.calculate(input);


    }
}
