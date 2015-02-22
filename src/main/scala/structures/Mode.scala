package radical_cadence.dsl

object ScaleDegree extends Enumeration {
  type ScaleDegree = Value
  val I, II, III, IV, V, VI, VII = Value
} 

object KeySignatureSpelling extends Enumeration {
  type KeySignatureSpelling = Value
  val Mixed, Sharps, Flats = Value
}

trait HasKeySignatureSpelling {
  def getSpelling: KeySignatureSpelling.Value
}

trait Mode extends Music with HasKeySignatureSpelling {
  def getDegreePitch(d: ScaleDegree.Value): Pitch
}
