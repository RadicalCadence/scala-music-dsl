package radical_cadence.dsl

import org.scalatest.FunSuite

class MeasureTests extends FunSuite {

  import parser._

  test("Measure - Parsed simple measure") {
    import PitchDecorator._
    import PitchClass._

    assert(m"| C4 E4 G4 C'4 |" == Staff(Measure(TimeSignature(4,4), 
      Note(Pitch(C,Blank,0),Beat(1,4)), Note(Pitch(E,Blank,0),Beat(1,4)),
      Note(Pitch(G,Blank,0),Beat(1,4)), Note(Pitch(C,Blank,1),Beat(1,4)))))
  }

  test("Measure - Parsed simple measure + decorators") {
    import PitchDecorator._
    import PitchClass._

    assert(m"| C#4 F4 G#4 C#'4 |" == Staff(Measure(TimeSignature(4,4),
      Note(Pitch(C,Sharp,0),Beat(1,4)), Note(Pitch(F,Blank,0),Beat(1,4)),
      Note(Pitch(G,Sharp,0),Beat(1,4)), Note(Pitch(C,Sharp,1),Beat(1,4)))))
  }

  test("Measure - Parsed simple measure + decorators + time signature") {
    import PitchDecorator._
    import PitchClass._

    assert(m"| [3,4] C#4 F4 G#4 |" == Staff(Measure(TimeSignature(3,4),
      Note(Pitch(C,Sharp,0),Beat(1,4)), Note(Pitch(F,Blank,0),Beat(1,4)),
      Note(Pitch(G,Sharp,0),Beat(1,4)))))
  }

}
