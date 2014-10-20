package fuck;

import com.example.ConnectionPool4Tests;
import com.example.DatabaseManager;
import com.example.SaveState;
import fuck.inductivitylearning.Action;
import fuck.inductivitylearning.Environment;
import robocode.RobocodeFileWriter;
import robocode.control.events.*;
import robocode.control.snapshot.IRobotSnapshot;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user50 on 26.09.2014.
 */
public class Listener implements IBattleListener {

    DatabaseManager databaseManager = new DatabaseManager(new ConnectionPool4Tests());

    @Override
    public void onBattleStarted(BattleStartedEvent battleStartedEvent) {

    }

    @Override
    public void onBattleFinished(BattleFinishedEvent battleFinishedEvent) {

    }

    @Override
    public void onBattleCompleted(BattleCompletedEvent battleCompletedEvent) {

    }

    @Override
    public void onBattlePaused(BattlePausedEvent battlePausedEvent) {

    }

    @Override
    public void onBattleResumed(BattleResumedEvent battleResumedEvent) {

    }

    @Override
    public void onRoundStarted(RoundStartedEvent roundStartedEvent) {

    }

    @Override
    public void onRoundEnded(RoundEndedEvent roundEndedEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("d:\\games\\robocode\\robots\\fuck\\SimpleFucker.data\\output.txt"));
                    List<SaveState> states = new ArrayList<SaveState>();
                    String line = null;
                    while ( (line = reader.readLine()) != null)
                    {
                        if (line.isEmpty())
                            continue;

                        System.out.println(line);
                        String[] strings = line.split(";");
//                        states.add(new SaveState(new Action(Double.valueOf(strings[0]),
//                                Double.valueOf(strings[1])),
//                                new Environment(
//                                Double.valueOf(strings[2]),
//                                Double.valueOf(strings[3]),
//                                Double.valueOf(strings[4]),
//                                Double.valueOf(strings[5])
//                                ),
//                                new Environment(
//                                        Double.valueOf(strings[6]),
//                                        Double.valueOf(strings[7]),
//                                        Double.valueOf(strings[8]),
//                                        Double.valueOf(strings[9])
//                                )
//                        ));

                    }

                    reader.close();

                    try {
                        for (SaveState state : states){
                            databaseManager.executeUpdate(state);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void onTurnStarted(TurnStartedEvent turnStartedEvent) {

    }

    @Override
    public void onTurnEnded(TurnEndedEvent turnEndedEvent) {

    }

    @Override
    public void onBattleMessage(BattleMessageEvent battleMessageEvent) {

    }

    @Override
    public void onBattleError(BattleErrorEvent battleErrorEvent) {

    }


}
