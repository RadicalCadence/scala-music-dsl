package radical_cadence.dsl

import org.scalatest.FunSuite

class PitchTests extends FunSuite {

  import parser._

  test("Pitch - String interpretation") {
    assert(Pitch(PitchClass.D,PitchDecorator.Blank,0)== Pitch("d"))
  }
  
  test("Pitch - String interpretation w/ flat decorator") {
    assert(Pitch(PitchClass.E,PitchDecorator.Flat,0) == Pitch("e-"))
  }

  test("Pitch - String interpretation w/ # decorator + positive octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.Sharp,2) == Pitch("d#''"))
  }

  test("Pitch - String interpretation w/ X decorator + negative octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.DoubleSharp,-2) == Pitch("dX,,"))
  }

  test("Pitch - toPitchNumber") {
    assert(Pitch(PitchClass.D,PitchDecorator.DoubleSharp,-2).toPitchNumber == -20)
  }
  
  test("Pitch - fromPitchNumber") {
    assert(Pitch(0) == Pitch("C"))
    assert(Pitch(9) == Pitch("A"))
    assert(Pitch(15) == Pitch("E-'"))
    assert(Pitch(-3) == Pitch("A,"))
    assert(Pitch(-14) == Pitch("B-,,"))
  }

  test("Pitch - from DSL parser") {
    assert(m"C" == Pitch("C"))
    assert(m"D#" == Pitch("D#"))
  }
}
