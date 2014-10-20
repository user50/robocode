package regression;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public interface Regressor extends Serializable {

    public double calculate(Map<String,Double> variables);
}
