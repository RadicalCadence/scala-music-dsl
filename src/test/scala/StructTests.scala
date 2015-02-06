package music_dsl

import org.scalatest.FunSuite

class StructTests extends FunSuite {

  import structures._

  test("NamedPitch - String interpetation") {
    assert(NamedPitch(PitchClass.D,PitchDecorator.Blank,0)== NamedPitch("d"))
  }
  
  test("NamedPitch - String intepretation w/ flat decorator") {
    assert(NamedPitch(PitchClass.E,PitchDecorator.Flat,0) == NamedPitch("e-"))
  }

  test("NamedPitch - String intepretation w/ # decorator + positive octave") {
    assert(NamedPitch(PitchClass.D,PitchDecorator.Sharp,2) == NamedPitch("d#''"))
  }

  test("NamedPitch - String intepretation w/ X decorator + negative octave") {
    assert(NamedPitch(PitchClass.D,PitchDecorator.DoubleSharp,-2) == NamedPitch("dX,,"))
  }

  test("NamedPitch - toPitchNumber") {
    assert(NamedPitch(PitchClass.D,PitchDecorator.DoubleSharp,-2).toPitchNumber == -20)
  }
  
  test("NamedPitch - fromPitchNumber") {
    assert(NamedPitch(9) == NamedPitch("A"))
    assert(NamedPitch(15) == NamedPitch("E-'"))
    assert(NamedPitch(-3) == NamedPitch("A,"))
    assert(NamedPitch(-14) == NamedPitch("B-,,"))
  }
}
