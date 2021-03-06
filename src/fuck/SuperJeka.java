package fuck;

import brainstechnology.Brain;
import brainstechnology.inductivitylearning.PreProcess;
import brainstechnology.inductivitylearning.StatePredictor;
import brainstechnology.inductivitylearning.UtilityFunction;
import brainstechnology.optimization.SimulatedAnnealingOptimizer;
import brainstechnology.predictors.adaptive.EncogDirectionPredictor;
import brainstechnology.predictors.adaptive.EncogFullStatePredictor;
import brainstechnology.predictors.adaptive.EncogGunDirectionPredictor;
import brainstechnology.predictors.adaptive.EncogVelocityPredictor;
import brainstechnology.predictors.nonadaprive.*;
import brainstechnology.preprocessors.DirectionToEnemy;
import brainstechnology.preprocessors.GunHearing2GunDirection;
import brainstechnology.preprocessors.Hearing2Direction;
import brainstechnology.utility.*;
import robocode.BattleEndedEvent;

import java.io.Serializable;

public class SuperJeka extends AbstractJeka {

    @Override
    protected void init() {
        UtilityFunction calculator = new UtilityFunction();
//        calculator.add(new BasedOnNeuralNetwork(getDataFile(BasedOnNeuralNetwork.FILE)));
        calculator.add(new KeepGunDirectionToEnemy());
        calculator.add(new KeepHighVelocity());
        calculator.add(new KeepDistanceToEnemy());
        calculator.add(new AvoidWall());
        calculator.add(new KeepFiringLineTowardsEnemySpeed());

        StatePredictor statePredictor = new StatePredictor();
//        statePredictor.add(new EncogFullStatePredictor(this));
        statePredictor.add(new EncogVelocityPredictor());
        statePredictor.add(new EncogDirectionPredictor(this));
        statePredictor.add(new DirectionPredictor());
        statePredictor.add(new PositionPredictor());
        statePredictor.add(new EncogGunDirectionPredictor(this));
        statePredictor.add(new DistanceToEnemyPredictor());
        statePredictor.add(new EnemyDirectionPredictor());

        brain = new Brain(statePredictor, new SimulatedAnnealingOptimizer(), calculator,
                new PreProcess()
                        .with(new Hearing2Direction())
                        .with(new GunHearing2GunDirection())
                        .with(new DirectionToEnemy())
        );
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
        InputOutputUtil.save((Serializable) snapshots, getDataFile("snapshots"));

    }


}
