package fuck;

import fuck.regression.RegressionBuilder;
import fuck.regression.SinMyHeadingRegressionBuilder;
import regression.Regression;

/**
 * Created by user50 on 12.10.2014.
 */
public enum StateParameter {

    sinMyHeading(new SinMyHeadingRegressionBuilder()), cosMyHeading(null), velocity(null), x(null), y(null);

    private RegressionBuilder regressionBuilder;

    StateParameter(RegressionBuilder regressorBuilder) {
        this.regressionBuilder = regressorBuilder;
    }

    public RegressionBuilder getRegressionBuilder() {
        return regressionBuilder;
    }
}
