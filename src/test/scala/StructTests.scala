package music_dsl

import org.scalatest.FunSuite

class StructTests extends FunSuite {

  import structures._

  test("Pitch - String interpetation") {
    assert(Pitch(PitchClass.D,PitchDecorator.Blank,0)== Pitch("d"))
  }
  
  test("Pitch - String intepretation w/ flat decorator") {
    assert(Pitch(PitchClass.E,PitchDecorator.Flat,0) == Pitch("e-"))
  }

  test("Pitch - String intepretation w/ # decorator + positive octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.Sharp,2) == Pitch("d#''"))
  }

  test("Pitch - String intepretation w/ X decorator + negative octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.DoubleSharp,-2) == Pitch("dX,,"))
  }

  test("Pitch - toPitchNumber") {
    assert(Pitch(PitchClass.D,PitchDecorator.DoubleSharp,-2).toPitchNumber == -20)
  }
}
