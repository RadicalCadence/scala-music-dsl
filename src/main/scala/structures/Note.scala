package radical_cadence.dsl

case class Note(pitch: Pitch, duration: Beat) extends Music {
  override def toString: String = s"<${pitch.toString} $duration>"
  def asLy: String = s"${pitch.asLy}${duration.asLy}"
  def asRc: String = s"${pitch.asRc}${duration.asRc}"
}
