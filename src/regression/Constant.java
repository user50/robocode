package regression;

import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public class Constant implements Regressor {
    @Override
    public double calculate(Map<String, Double> variables) {
        return 1;
    }
}
