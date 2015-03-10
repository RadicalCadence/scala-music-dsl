package radical_cadence.dsl

import org.scalatest.FunSuite

class StaffTests extends FunSuite {

  import parser._

  test("Staff - Parse multiple measures") {
    import PitchDecorator._
    import PitchClass._

    assert(m"| C4 E4 G4 C'4 | D4 F4 A4 D'4 |" == Staff(Measure(TimeSignature(4,4),
      Note(Pitch(C,Blank,0),Beat(1,4)),Note(Pitch(E,Blank,0),Beat(1,4)),
      Note(Pitch(G,Blank,0),Beat(1,4)),Note(Pitch(C,Blank,1),Beat(1,4))),
      Measure(TimeSignature(4,4),Note(Pitch(D,Blank,0),Beat(1,4)),
      Note(Pitch(F,Blank,0),Beat(1,4)),Note(Pitch(A,Blank,0),Beat(1,4)),
      Note(Pitch(D,Blank,1),Beat(1,4)))))
  }

}
