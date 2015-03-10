package radical_cadence.dsl

import org.scalatest.FunSuite

class NoteTests extends FunSuite {
  
  import parser._

  test("Simple DSL interpretation") {
    assert(m"C4" == Note(Pitch("C"), Beat(1,4)))
  }

  test("Simple DSL interpretation w/ decorator") {
    assert(m"C#4" == Note(Pitch("C#"), Beat(1,4)))
  }
  
  test("Simple DSL interpretation w/ decorator and octave") {
    assert(m"C#''4" == Note(Pitch("C#''"), Beat(1,4)))
  }

}
