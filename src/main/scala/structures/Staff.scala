package radical_cadence.dsl

case class Staff(music: Music*) extends Music {
  override def foreach[U](f: Music => U) = music foreach f
  override def toString: String = s"Staff($music)"
}
