package radical_cadence.dsl

case class Beat(num: Int = 1, denom: Int = 4) extends Music {
  require((denom & (denom - 1)) == 0, "Beat denominator must be a power of two.")
  override def toString: String = s"Beat[$num,$denom]"
}

case class TimeSignature(num: Int = 4, denom: Int = 4) extends Music {
  override def toString: String = s"TimeSig[$num,$denom]"
}
