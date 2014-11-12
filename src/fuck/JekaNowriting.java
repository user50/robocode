package fuck;

import brainstechnology.Brain;
import brainstechnology.inductivitylearning.PreProcess;
import brainstechnology.inductivitylearning.StatePredictor;
import brainstechnology.inductivitylearning.UtilityFunction;
import brainstechnology.optimization.SimulatedAnnealingOptimizer;
import brainstechnology.predictors.nonadaprive.*;
import brainstechnology.preprocessors.DirectionToEnemy;
import brainstechnology.preprocessors.GunHearing2GunDirection;
import brainstechnology.preprocessors.Hearing2Direction;
import brainstechnology.utility.AvoidWall;
import brainstechnology.utility.KeepDistanceToEnemy;
import brainstechnology.utility.KeepGunDirectionToEnemy;
import brainstechnology.utility.KeepHighVelocity;
import robocode.BattleEndedEvent;

/**
 * Created by user50 on 01.11.2014.
 */
public class JekaNoWriting extends AbstractJeka {

    public JekaNoWriting()
    {
        UtilityFunction calculator = new UtilityFunction();
//        calculator.add(new BasedOnNeuralNetwork(getDataFile(BasedOnNeuralNetwork.FILE)));
        calculator.add(new KeepGunDirectionToEnemy());
        calculator.add(new KeepHighVelocity());
        calculator.add(new KeepDistanceToEnemy());
        calculator.add(new AvoidWall());

        StatePredictor statePredictor = new StatePredictor();
        statePredictor.add(new VelocityPredictor());
        statePredictor.add(new DirectionPredictor());
        statePredictor.add(new PositionPredictor());
        statePredictor.add(new GunDirectionPredictor());
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
        //nothing to do
    }
}
