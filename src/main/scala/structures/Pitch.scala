package radical_cadence.dsl

case class Pitch(pitchClass: PitchClass.Value, decorator: PitchDecorator.Value,
  octave: Int) extends Music with Ordered[Pitch] {

  def toPitchNumber: Int = { 
    PitchClass.toPitchNumber(pitchClass)+PitchDecorator.toPitchNumber(decorator)+(octave*12)
  }

  override def toString: String = { 
    var octaves = "";
    if (octave >= 0) { octaves = "'" * octave } 
    else { octaves = "," * Math.abs(octave) }

    pitchClass.toString + PitchDecorator.toString(decorator) + octaves
  }

  def asLy: String = this.toString.toLowerCase
  def asDSL: String = this.toString.toLowerCase

  def compare(that: Pitch): Int = this.toPitchNumber.compare(that.toPitchNumber)
}

object PitchClass extends Enumeration {
  type PitchClass = Value
  val C, D, E, F, G, A, B = Value

  private val midi = Map(C -> 0, D -> 2, E -> 4, F -> 5, G -> 7,
    A -> 9, B -> 11)
  
  def apply(s: String):PitchClass = withName(s.toUpperCase)
  def toPitchNumber(p: PitchClass): Int = midi.getOrElse(p, 0)

  def valueStream = Stream.continually(PitchClass.values).flatten
}

object PitchDecorator extends Enumeration {
  type PitchDecorator = Value
  val Blank, Natural, Sharp, Flat, DoubleSharp, DoubleFlat = Value

  private val dec = Map("n" -> Natural, "#" -> Sharp, "-" -> Flat, 
    "x" -> DoubleSharp, "##" -> DoubleSharp, "_" -> DoubleFlat)
  private val midi = Map(Sharp -> 1, Flat -> -1, DoubleSharp -> 2, 
    DoubleFlat -> -2)

  def apply(s: String):PitchDecorator = dec.getOrElse(s.toLowerCase,Blank)
  def toPitchNumber(d: PitchDecorator): Int = midi.getOrElse(d, 0)
  def toString(d:PitchDecorator.Value): String = dec.map(_.swap).getOrElse(d, "")
}

object Pitch {
  import PitchClass._
  import PitchDecorator._

  private val r = """([a-g,A-G])([n|#|x|X|\-|_]?)([,|']*)""".r

  private val midi = Map(0 -> "C", 1 -> "C#", 2 -> "D", 3 -> "E-",
    4 -> "E", 5 -> "F", 6 -> "F#", 7 -> "G", 8 -> "A-", 9 -> "A",
    10 -> "B-", 11 -> "B")

  def apply(s:String): Pitch = s match {
    case r(p) =>     new Pitch(PitchClass(p),Blank,0)
    case r(p,d) =>   new Pitch(PitchClass(p),PitchDecorator(d), 0)
    case r(p,d,o) => new Pitch(PitchClass(p),PitchDecorator(d),
      o.count(c => (c == ''')) - o.count(c => (c == ',')))
    case _ => ???
  }

  def apply(i: Int): Pitch = { 

    var octaves = "";
    if(i >= 0) {
      octaves = "'" * (i / 12)
    } else {
      octaves = "," * (Math.abs(i-12) / 12)
    }

    Pitch((midi.getOrElse(Math.abs((i+48)%12),"C")+octaves))
  }

  def apply(pc: PitchClass.Value): Pitch = new Pitch(pc, Blank, 0)

}
