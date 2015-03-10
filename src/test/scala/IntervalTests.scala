package radical_cadence.dsl

import org.scalatest.FunSuite

class IntervalTests extends FunSuite {

  test("Interval - Parsed intervals") {
    assert(Interval("M2") == Interval(IntervalQuality.Major, 2))
    assert(Interval("-aug4") == Interval(IntervalQuality.Augmented, -4))
    assert(Interval("+m6") == Interval(IntervalQuality.Minor, 6))
  }

  test("Interval - Generate Interval from Pitches") {
    assert(Interval(Pitch("C"), Pitch("E")) == Interval(IntervalQuality.Major, 3))
    assert(Interval(Pitch("D"), Pitch("F#")) == Interval(IntervalQuality.Major, 3))
    assert(Interval(Pitch("C"), Pitch("E-")) == Interval(IntervalQuality.Minor, 3))
    assert(Interval(Pitch("C"), Pitch("G")) == Interval(IntervalQuality.Perfect, 5))
    assert(Interval(Pitch("A"), Pitch("E'")) == Interval(IntervalQuality.Perfect, 5))
    assert(Interval(Pitch("C"), Pitch("C'")) == Interval(IntervalQuality.Octave, 8))
    assert(Interval(Pitch("C"), Pitch("D'")) == Interval(IntervalQuality.Major, 9))
  }

  test("Interval - Get Target Pitch") {
    assert(Interval(IntervalQuality.Major, 3).getPitch(Pitch("C")) == Pitch("E"))
    assert(Interval(IntervalQuality.Perfect, 5).getPitch(Pitch("C")) == Pitch("G"))
    assert(Interval(IntervalQuality.Octave, 8).getPitch(Pitch("C")) == Pitch("C'"))
  }

}
