package radical_cadence.dsl

case class Chord(root: Pitch, quality: ChordQuality.Value)

object Chord {
  private val root = """([a-g,A-G])([n|#|x|X|\-|_]?)([,|']*)"""
  private val qual = """(M|m|maj|min)"""
  private val r = s"""$root:$qual""".r

}

object ChordQuality extends Enumeration {
  type ChordQuality = Value
  val Major, Minor, Diminshed, Augmented, Seventh, MajorSeventh = Value
}
