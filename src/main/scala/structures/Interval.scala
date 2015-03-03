package radical_cadence.dsl

case class Interval(quality: IntervalQuality.Value, number: Int) extends Music {
  
  //TODO: We also need to test for valid quality+number combos
  require(number != 0)
  override def toString:String = {
    (if(number>0) "+" else "-")+IntervalQuality.toString(quality)+Math.abs(number).toString
  }
  
  //TODO: Implement!
  def asLy: String = ""
  def asRc: String = ""

  def getPitch(from: Pitch): Pitch = {
    def pitchClass = PitchClass.valueStream.dropWhile(_ != from.pitchClass)(number-1)
    //TODO: Calculate the PitchDecorator based on the Mode
    Pitch(pitchClass, PitchDecorator.Blank, number/8)
  }

}

object IntervalQuality extends Enumeration {
  type IntervalQuality = Value
  val Unison, Major, Minor, Perfect, Augmented, Diminished, Octave = Value

  val qual = Map("M" -> Major, "maj" -> Major, "m" -> Minor, "min" -> Minor,
    "P" -> Perfect, "aug" -> Augmented, "dim" -> Diminished, "U" -> Unison,
    "O" -> Octave)

  def apply(s: String): IntervalQuality = qual.getOrElse(s, Unison)
  def toString(q: IntervalQuality.Value):String = qual.map(_.swap).getOrElse(q,"")
}

object Interval {
  import KeySignatureSpelling._
  import IntervalQuality._

  val q = Map(0 -> Octave, 1 -> Minor, 2 -> Major, 3 -> Minor,
    4 -> Major, 5 -> Perfect, 6 -> Diminished, 7 -> Perfect, 
    8 -> Minor, 9 -> Major, 10 -> Minor, 11 -> Major)

  val r = """^([+,-]?)(M|m|P|aug|dim)(\d+)""".r

  def apply(s: String): Interval = s match {
    case r(q,d) => new Interval(IntervalQuality(q), d.toInt)
    case r(p,q,d) => new Interval(IntervalQuality(q), (p+d).toInt)
  }

  def apply(i: Int): Interval = Interval(Pitch(0), Pitch(i))

  def apply(p1:Pitch,p2:Pitch):Interval = {
    if(p1 != p2) {
      val pitches = (p1.toPitchNumber to p2.toPitchNumber).collect { case i => Pitch(i) }
      val distance = pitches.drop(1).foldLeft(List(p1.pitchClass)) { 
        (a,c) => { if(a.last != c.pitchClass) { a :+ c.pitchClass } else a } }
      val qual = q.getOrElse((p2.toPitchNumber-p1.toPitchNumber) % 12, Unison)
      new Interval(qual, distance.size)
    } else {
      new Interval(Unison, 1)
    }
  }
}

