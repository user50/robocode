package fuck.regression;

import fuck.Action;
import fuck.StateParameter;
import regression.Constant;
import regression.Regression;
import regression.Regressor;
import regression.SimplePow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user50 on 12.10.2014.
 */
public class SinMyHeadingRegressionBuilder implements RegressionBuilder {
    @Override
    public Regression build() {
        List<Regressor> regressors = new ArrayList<>();

        regressors.add(new Constant());
        regressors.add(new SimplePow(Action.rotate.name(), 1));
        regressors.add(new SimplePow(StateParameter.sinMyHeading.name(), 1));
        regressors.add(new SimplePow(StateParameter.cosMyHeading.name(), 1));
        regressors.add(new SimplePow(StateParameter.velocity.name(), 1));

        return new Regression(regressors);
    }
}
