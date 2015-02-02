package music_dsl

import org.scalatest.FunSuite

class StructTests extends FunSuite {

  import structures._

  test("Pitch - String interpetation") {
    val p1 = Pitch(PitchClass.D,PitchDecorator.Blank,0)
    val p2 = Pitch("d")

    assert(p1 == p2)
  }
  
  test("Pitch - String intepretation w/ decorator") {
    assert(Pitch(PitchClass.D,PitchDecorator.Sharp,0) == Pitch("d#"))
  }

  test("Pitch - String intepretation w/ # decorator + positive octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.Sharp,2) == Pitch("d#''"))
  }

  test("Pitch - String intepretation w/ X decorator + negative octave") {
    assert(Pitch(PitchClass.D,PitchDecorator.DoubleSharp,-2) == Pitch("dX,,"))
  }
}
