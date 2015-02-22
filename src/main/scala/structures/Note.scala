package radical_cadence.dsl

case class Note(pitch: Pitch, duration: Beat) extends Music {
  override def toString: String = s"<${pitch.toString} $duration>"
}
