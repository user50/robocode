package com.example;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class VectorAlgebra
{
  public static double getLength(Vector vector)
  {
    return Math.sqrt( vector.getA() * vector.getA() + vector.getB() * vector.getB() );
  }

  public static Vector rotate(Vector vector, double angle)
  {
    double a = vector.getA();
    double b = vector.getB();

    return new Vector( a*cos( angle ) - b*sin( angle ), a*sin( angle ) + b*cos( angle ) );
  }

  public static Vector prod(Vector vector, double scalar)
  {
    return new Vector( vector.getA() * scalar, vector.getB() * scalar );
  }

  public static Vector sum( Vector meToEnemy, Vector me )
  {
    return new Vector( meToEnemy.getA() + me.getA(), meToEnemy.getB() + me.getB() );
  }

  public static double scalarProd(Vector v1, Vector v2)
  {
    return v1.getA() * v2.getA() + v1.getB() * v2.getB();
  }

  public static double vectorProd(Vector v1, Vector v2)
  {
    return v1.getA() * v2.getB() - v1.getB() * v2.getA();
  }

  public static double getAngleBetween(Vector v1, Vector v2)
  {
    double scalarProd = scalarProd( v1, v2 );
    double vectorProd = vectorProd( v1, v2 );

    double sign = vectorProd == 0 ? Math.signum( scalarProd ) : Math.signum( vectorProd ) ;

    return Math.acos( scalarProd / (getLength( v1 ) * getLength( v2 )) ) * sign;
  }

  public static Vector diff( Vector v1, Vector v2 )
  {
    return new Vector( v1.getA() - v2.getA(), v1.getB() - v2.getB() );
  }
}
