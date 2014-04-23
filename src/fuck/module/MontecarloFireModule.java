package fuck.module;

import com.example.Vector;
import fuck.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 21.04.14
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class MonteCarloFireModule implements FireModule
{
  private double power = 2;

  @Override
  public double power( Vector enemy, Vector me )
  {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isFire( State currentState )
  {
    Random randomizer = new Random(  );
    List<State> states = new ArrayList<State>(  );

    for( int i=0;i<20;i++ )
      states.add( currentState.nextState() );

    boolean stop = false;
    while( !stop )
    {
      List<State> nextStates = new ArrayList<State>(  );
      while( nextStates.size() < 20 )
      {
        State state = states.get( randomizer.nextInt(states.size()) ).nextState();
        if( isTerminal(state) )


        nextStates.add();
      }
    }





    return false;
  }

  private boolean isTerminal( State state )
  {
    return false;
  }
}
