package algebra;

import static java.lang.Math.*;

/**
 * Created with IntelliJ IDEA.
 * User: neuser50
 * Date: 20.04.14
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class VectorAlgebra
{
  public static double length( Vector vector )
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

    return Math.acos( scalarProd / (length( v1 ) * length( v2 )) ) * sign;
  }

  public static Vector diff( Vector v1, Vector v2 )
  {
    return new Vector( v1.getA() - v2.getA(), v1.getB() - v2.getB() );
  }

  public static Vector normal( Vector v )
  {
    return new Vector( -v.getB(), v.getA() );
  }

  public static Vector inverse(Vector vector)
  {
    return prod( vector, -1 );
  }

  public static Vector getRandomDirectedVector(double length)
  {
    Vector randomVector = new Vector( random() - 0.5, random() - 0.5 );

    return prod( randomVector, length/ length( randomVector ) );
  }

  public static Vector normalize(Vector vector)
  {
    return prod( vector, 1/ length( vector ) );
  }

  public static boolean isIntersect(Vector A1, Vector A2, Vector B1, Vector B2)
  {
    Vector A = diff( A1, A2 );
    Vector B = diff( B2, B1 );

    Vector C = diff( B2, A2 );

    double determinant = A.getA()* B.getB() - A.getB()*B.getA();

    double d1 = C.getA()* B.getB() - C.getB()*B.getA();
    double d2 = A.getA()* C.getB() - A.getB()*C.getA();

    if( determinant == 0 && d1 == 0 && d2 == 0 )
      return true;

    if( determinant == 0 )
      return false;

    double k1 = d1/determinant;
    double k2 = d2/determinant;

    return k1 >= 0 && k1 <= 1 && k2 >= 0 && k2 <= 1;
  }

  public static boolean isLineIntersectRectangle(Vector X1, Vector X2, Vector center, double length)
  {
    return isIntersect( X1, X2, sum( center, new Vector( length / 2, length / 2 ) ), sum( center, new Vector( -length / 2, length / 2 ) ) )
            || isIntersect( X1, X2, sum( center, new Vector( length / 2, length / 2 ) ), sum( center, new Vector( length / 2, -length / 2 ) ) )
            || isIntersect( X1, X2, sum( center, new Vector( -length / 2, -length / 2 ) ), sum( center, new Vector( -length / 2, length / 2 ) ) )
            || isIntersect( X1, X2, sum( center, new Vector( -length / 2, -length / 2 ) ), sum( center, new Vector( length / 2, -length / 2 ) ) );
  }

}
