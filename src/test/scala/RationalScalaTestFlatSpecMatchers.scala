package radical_cadence.util

import org.scalatest._

import rational._

class RationalScalaTestFlatSpecMatchers extends FlatSpec
    with Matchers {

  // RationalFlatSpec.GCD
  "GCD involving 0" should "give y for gcd(0, y)" in {
    gcd(0, 5) should be(5)
  }

  it should "give x for gcd(x, 0)" in {
    gcd(0, 5) should be(5)
  }

  "GCD not involving 0" should "be 3" in {
    gcd(3 * 1, 3 * 3) should be(3)
  }

  it should "be 5" in {
    gcd(5 * 1, 5 * 5) should be(5)
  }
  // RationalFlatSpec.Initializing
  "Initializing" should "reduce fractions (+,+)" in {
    val r1 = new Rational(2, 4)
    r1._n should be(1)
    r1._d should be(2)
  }

  it should "reduce fractions (-,+)" in {
    val r1 = new Rational(-2, 4)
    r1._n should be(-1)
    r1._d should be(2)
  }

  it should "reduce fractions (-,-)" in {
    val r1 = new Rational(-3, -6)
    r1._n should be(1)
    r1._d should be(2)
  }

  it should "prohibit zero denominator" in {
    a[ArithmeticException] should be thrownBy {
      new Rational(3, 0)
    }
  }

  // RationalFlatSpec.Arithmetic
  "Arithmetic" should "perform addition" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 + r2 should ===(new Rational(36, 64))
    r1 + r2 should be(new Rational(36, 64))
  }

  it should "perform subtraction" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 - r2 should be(new Rational(58, 64))
  }

  it should "perform multiplication" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 * r2 should be(new Rational(47 * -11, 64 * 64))
  }

  it should "perform division" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1 / r2 should be(new Rational(47, -11))
  }

  it should "perform the reciprocal" in {
    val r1 = new Rational(47, 64)
    r1.reciprocal() should be(new Rational(64, 47))
  }

  it should "perform negation" in {
    val r1 = new Rational(47, 64)
    val r2 = new Rational(-11, 64)
    r1.negate() should be(new Rational(-47, 64))
  }

  // RationalFlatSpec.Comparisons

  "Comparisons" should "perform ==" in {
    val r1 = new Rational(2, 4)
    val r2 = new Rational(1, 2)
    info("numeric equality (==) works")
    assert(r1 == r2)
    info("object equality works, e.g. equals()")
    assert(r1.equals(r2))
    info("hashcode() works")
    assert(r1.hashCode() == r2.hashCode())

  }
  it should "perform <" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    info("operator < works")
    assert(r1 < r2)
  }

  it should "perform >" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    info("operator > works")
    assert(r2 > r1)
  }

  it should "perform <= for something < and for something =" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    info("operator <= works for < case")
    assert(r1 <= r2)
    info("operator <= works for == case")
    assert(r2 <= r3)
  }

  it should "perform >= for something > and for something =" in {
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    info("operator >= works for > case")
    assert(r2 >= r1)
    info("operator >= works for == case")
    assert(r2 >= r3)
  }

  // RationalFlatSpec.Collectionss
  "Within collections" should "work" in {
    info("on Set[Rational]")
    val r1 = new Rational(-3, 6)
    val r2 = new Rational(2, 4)
    val r3 = new Rational(1, 2)
    val s = Set(r1, r2, r3)
    assert(s.size == 2)
  }

  // RationalFlatSpec.PatternMatching
  "Pattern matching" should "work" in {
    Rational(3, 9) match {
      case Rational(1, 3) => ()
    }
  }

  it should "not work" in {
    Rational(3, 9) match {
      case Rational(2, 4) => ??? // Not implemented
      case _              => ()
    }
  }
  // RationalFlatSpec.End

}
