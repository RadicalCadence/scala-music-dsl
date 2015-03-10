package radical_cadence.dsl

case class Beat(num: Int = 1, denom: Int = 4) extends Music {
  require((denom & (denom - 1)) == 0, "Beat denominator must be a power of two.")
  override def toString: String = s"Beat[$num,$denom]"

  //TODO: This is temporary, must handle triplets!
  def asLy: String = denom.toString
  def asRc: String = denom.toString
}

case class TimeSignature(num: Int = 4, denom: Int = 4) extends Music {
  override def toString: String = s"TimeSig[$num,$denom]"

  //TODO: Implement!
  def asLy: String = ""
  def asRc: String = ""
}

object TimeSignature {
  private val r = """\[(\d),(\d)\]""".r

  def apply(str: String): TimeSignature = str match {
    case r(n,d) => new TimeSignature(n.toInt,d.toInt)
  }
}
