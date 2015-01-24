package music_dsl

import org.scalatest.FunSuite

class StructTests extends FunSuite {

  import structures._

  test("Basic Pitch - String interpetation") {
    val p1 = Pitch(14)
    val p2 = Pitch("c##'")
    val p3 = Pitch("cx'")

    assert((p1 == p2) && (p2 == p3))
  }
}
