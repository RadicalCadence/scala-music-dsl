package radical_cadence.dsl

case class Staff(music: Music*) extends Music {
  override def foreach[U](f: Music => U) = music foreach f
  override def toString: String = s"Staff($music)"

  def asLy: String = s"""{ ${music.foldLeft("")((s, m) => s+m.asLy+" ")}}"""
  def asDSL: String = s"""{ ${music.foldLeft("")((s, m) => s+m.asDSL+" ")}}"""
}
