package com.example;

import algebra.Vector;
import org.junit.Test;

import static algebra.VectorAlgebra.getAngleBetween;
import static algebra.VectorAlgebra.isLineIntersectRectangle;
import static algebra.VectorAlgebra.rotate;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 */
public class VectorAlgebraTest
{
  private final static double E = 0.0001;

  @Test
  public void testRotateVector() throws Exception
  {
    Vector rotatedVector = rotate( new Vector( 1, 1 ), PI / 2 );

    assertTrue( abs( rotatedVector.getA() + 1 ) < E);
    assertTrue( abs( rotatedVector.getB() - 1 ) < E);
  }

  @Test
  public void testGetAngleBetweenOrthonormalVectors() throws Exception
  {
    double angle = getAngleBetween( new Vector( 1, 0 ), new Vector( 0, 1 ) );
    assertTrue( abs( angle - PI/2 ) < E  );

  }

  @Test
  public void testGetAngleBetweenCollinearVectors() throws Exception
  {
    double angle = getAngleBetween( new Vector( 1, 0 ), new Vector( 1, 0 ) );
    assertTrue( abs( angle - 0 ) < E  );
  }

  @Test
  public void testGetAngleBetweenAntiCollinearVectors() throws Exception
  {
    double angle = getAngleBetween( new Vector( 1, 0 ), new Vector( -1, 0 ) );
    assertTrue( abs( angle + PI ) < E  );
  }

  @Test
  public void testGetAngleBetweenTwoSimpleVectors() throws Exception
  {
    double angle = getAngleBetween( new Vector( 1, 1 ), new Vector( 0, 1 ) );
    assertTrue( abs( angle - PI/4 ) < E  );
  }

  @Test
  public void testIsLineIntersectRectangle() throws Exception
  {
    isLineIntersectRectangle( new Vector( 0, 0 ), new Vector( 130,90 ), new Vector( 50,50 ), 30 );

  }
}
