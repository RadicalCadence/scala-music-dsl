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

case class MajorScale(root: Pitch) extends Mode {
  import KeySignatureSpelling._

  def getSpelling: KeySignatureSpelling.Value = root match {
    case Pitch(PitchClass.C,_,_) => Sharps
    case Pitch(PitchClass.G,_,_) => Sharps
    case Pitch(PitchClass.D,_,_) => Sharps
    case Pitch(PitchClass.A,_,_) => Sharps
    case Pitch(PitchClass.E,_,_) => Sharps
    case Pitch(PitchClass.B,_,_) => Sharps
    case Pitch(PitchClass.F,_,_) => Flats
    case Pitch(_,PitchDecorator.Sharp,_) => Sharps
    case Pitch(_,PitchDecorator.Flat,_) => Flats
  }

  def getDegreePitch(d: ScaleDegree.Value): Pitch = d match {
    //TODO: Implement using Interval calculation
    case ScaleDegree.I => root
    case ScaleDegree.II => Interval("M2").getPitch(root)
    case ScaleDegree.III => Interval("M3").getPitch(root)
    case ScaleDegree.IV => Interval("P4").getPitch(root)
    case ScaleDegree.V => Interval("P5").getPitch(root)
    case ScaleDegree.VI => Interval("M6").getPitch(root)
    case ScaleDegree.VII => Interval("M7").getPitch(root)
  }

  def asLy: String = ""
  def asDSL: String = ""

}
