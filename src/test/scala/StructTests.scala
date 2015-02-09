package radical_cadence.dsl

import org.scalatest.FunSuite

class StructTests extends FunSuite {

  import structures._
  import parser._

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
  
  test("Pitch - fromPitchNumber") {
    assert(Pitch(9) == Pitch("A"))
    assert(Pitch(15) == Pitch("E-'"))
    assert(Pitch(-3) == Pitch("A,"))
    assert(Pitch(-14) == Pitch("B-,,"))
  }

  test("Measure - Parsed simple measure") {
    import structures.PitchDecorator._
    import structures.PitchClass._

    assert(m"| C4 E4 G4 C'4 |" == Staff(Measure(TimeSignature(4,4), 
      Note(Pitch(C,Blank,0),Beat(1,4)), Note(Pitch(E,Blank,0),Beat(1,4)),
      Note(Pitch(G,Blank,0),Beat(1,4)), Note(Pitch(C,Blank,1),Beat(1,4)))))
  }
}
