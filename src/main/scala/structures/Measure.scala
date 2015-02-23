package radical_cadence.dsl

case class Measure(timeSignature: TimeSignature, music: Music*) extends Music {
  override def foreach[U](f: Music => U) = music foreach f
  override def toString: String = s"Measure($timeSignature, $music)"

  def asLy: String = s"""${music.foldLeft("")((s, m) => s+m.asLy+" ")}|"""
  def asRc: String = s"""${music.foldLeft("")((s, m) => s+m.asRc+" ")}|"""
}
