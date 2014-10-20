package regression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user50 on 12.10.2014.
 */
public class Regression implements Serializable {

    List<Parameter> parameters = new ArrayList<Parameter>();

    public Regression(List<Regressor> regressors) {
        for (Regressor regressor : regressors)
            parameters.add(new Parameter(regressor, Math.random()));

    }

    public double calculate( Map<String,Double> inputs ) {
        double estimate = 0;

        for (Parameter parameter : parameters)
            estimate += parameter.value * parameter.regressor.calculate(inputs);

        return estimate;
    }

    public void adapt(Map<String,Double> inputs, double output, double alpha )
    {
        double estimate = calculate(inputs);

        for (Parameter parameter : parameters)
            parameter.value += alpha * (output - estimate) * parameter.regressor.calculate(inputs);

    }

    private static class Parameter implements Serializable
    {
        Regressor regressor;
        double value;

        private Parameter(Regressor regressor, double value) {
            this.regressor = regressor;
            this.value = value;
        }
    }
}
