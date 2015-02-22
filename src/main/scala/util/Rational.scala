package radical_cadence.util

package object rational {

  /*
   * This version of Rational is based on Martin Odersky's Scala by Example.
   * source: http://www.scala-lang.org/docu/files/ScalaByExample.pdf
   *
   * We include a number of additions/improvements based on Harrington and Thiruvathukal's C# Book,
   * which includes an elaboration of other needed methods and full unit tests.
   * source: http://introcs.cs.luc.edu
   */

  // begin-RationalMathUtility-gcd
  def gcd(x: Int, y: Int): Int = {
    if (x == 0) y
    else if (x < 0) gcd(-x, y)
    else if (y < 0) -gcd(x, -y)
    else gcd(y % x, x)
  }

  // end-RationalMathUtility-gcd

  trait NumericRational extends Numeric[Rational] {
    def minus(x: Rational, y: Rational): Rational = x - y

    def fromInt(x: Int): Rational = Rational(x, 1)

    def negate(x: Rational): Rational = x.negate()

    def plus(x: Rational, y: Rational): Rational = x + y

    def times(x: Rational, y: Rational): Rational = x * y

    def toDouble(x: Rational): Double = x._n.toDouble / x._d

    def toFloat(x: Rational): Float = x._n.toFloat / x._d

    def toInt(x: Rational): Int = x._n / x._d

    def toLong(x: Rational): Long = x._n.toLong / x._d.toLong

    def compare(x: Rational, y: Rational): Int = x.compare(y)
  }

  // begin-RationalClass
  class Rational(n: Int, d: Int) extends Ordered[Rational] with NumericRational {

    private val g = gcd(n, d)

    // RationalClass.Initialization
    val _n: Int = n / g
    val _d: Int = d / g
    // perform test quotient for possible ArithmeticException
    val _q = _n / _d

    // RationalClass.Arithmetic
    def +(that: Rational) =
      new Rational(
        _n * that._d + that._n * _d,
        _d * that._d
      )

    def -(that: Rational) =
      new Rational(
        _n * that._d - that._n * _d,
        _d * that._d
      )

    def *(that: Rational) =
      new Rational(_n * that._n, _d * that._d)

    def /(that: Rational) =
      new Rational(_n * that._d, _d * that._n)

    def reciprocal() =
      new Rational(_d, _n)

    def negate() =
      new Rational(-_n, _d)

    // RationalClass.Comparisons
    def compare(that: Rational) = _n * that._d - that._n * _d

    // RationalClass.Objects
    override def equals(o: Any) = o match {
      case that: Rational => compare(that) == 0
      case _              => false
    }

    override def hashCode = (_n.hashCode, _d.hashCode).hashCode 

    // RationalClass.Done

    override def toString: String = {
      "[" + _n + "/" + _d + "]"
    }

  }

  // end-RationalClass

  // begin-RationalObject
  object Rational {
    def apply(n: Int, d: Int) = new Rational(n, d)

    def unapply(r: Rational): Option[(Int, Int)] = Some((r._n, r._d))
  }

  // end-RationalObject

  implicit object NumericRational extends NumericRational

}
