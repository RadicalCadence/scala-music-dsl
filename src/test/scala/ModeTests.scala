package radical_cadence.dsl

import org.scalatest.FunSuite

class ModeTests extends FunSuite {

  test("Major Scale - Scale Degrees (C Major)") {
    val scale = MajorScale(Pitch("C"))
    assert(scale.getDegreePitch(ScaleDegree.I) == Pitch("C"))
    assert(scale.getDegreePitch(ScaleDegree.II) == Pitch("D"))
    assert(scale.getDegreePitch(ScaleDegree.III) == Pitch("E"))
    assert(scale.getDegreePitch(ScaleDegree.IV) == Pitch("F"))
    assert(scale.getDegreePitch(ScaleDegree.V) == Pitch("G"))
    assert(scale.getDegreePitch(ScaleDegree.VI) == Pitch("A"))
    assert(scale.getDegreePitch(ScaleDegree.VII) == Pitch("B"))
  }

  test("Major Scale - Scale Degrees (D Major)") {
    val scale = MajorScale(Pitch("D"))
    assert(scale.getDegreePitch(ScaleDegree.I) == Pitch("D"))
    assert(scale.getDegreePitch(ScaleDegree.II) == Pitch("E"))
    assert(scale.getDegreePitch(ScaleDegree.III) == Pitch("F#"))
    assert(scale.getDegreePitch(ScaleDegree.IV) == Pitch("G"))
    assert(scale.getDegreePitch(ScaleDegree.V) == Pitch("A"))
    assert(scale.getDegreePitch(ScaleDegree.VI) == Pitch("B"))
    assert(scale.getDegreePitch(ScaleDegree.VII) == Pitch("C#"))
  }

}
