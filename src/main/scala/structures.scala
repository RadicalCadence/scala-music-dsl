package radical_cadence.dsl

import scalaz._, Scalaz._

package object structures {

  trait Music extends Traversable[Music] {
    def foreach[U](f: Music => U) = f(this)
  }

  case class Beat(num: Int = 1, denom: Int = 4) extends Music {
    require((denom & (denom - 1)) == 0, "Beat denominator must be a power of two.")
    override def toString: String = s"Beat[$num,$denom]"
  }

  case class TimeSignature(num: Int = 4, denom: Int = 4) extends Music {
    override def toString: String = s"TimeSig[$num,$denom]"
  }

  case class Pitch(pitchClass: PitchClass.Value, 
    decorator: PitchDecorator.Value, octave: Int) extends Music {
    def toPitchNumber: Int = { PitchClass.toPitchNumber(pitchClass)+
     PitchDecorator.toPitchNumber(decorator) + (octave*12) }

    override def toString: String = { 
      var octaves = "";
      if (octave >= 0) { octaves = "+" * octave } 
      else { octaves = "-" * Math.abs(octave) }

      pitchClass.toString + PitchDecorator.toString(decorator) + octaves
    }
  }

  //TODO: Put all parsing into the DSLParser...
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
      if(i > 0) {
        octaves = "'" * (i / 12)
      } else {
        octaves = "," * (Math.abs(i-12) / 12)
      }

      Pitch((midi.getOrElse(Math.abs((i+48)%12),"C")+octaves))
    }
  }

  case class Note(pitch: Pitch, duration: Beat) extends Music {
   override def toString: String = s"<${pitch.toString} $duration>"
  }

  case class Measure(timeSignature: TimeSignature, music: Music*) extends Music {
    override def foreach[U](f: Music => U) = music foreach f
    override def toString: String = s"Measure($timeSignature, $music)"
  }

  case class Staff(music: Music*) extends Music {
    override def foreach[U](f: Music => U) = music foreach f
    override def toString: String = s"Staff($music)"
  }

  object PitchClass extends Enumeration {
    type PitchClass = Value
    val C, D, E, F, G, A, B = Value

    private val midi = Map(C -> 0, D -> 2, E -> 4, F -> 5, G -> 7,
      A -> 9, B -> 11)
    
    def apply(s: String):PitchClass = withName(s.toUpperCase)
    def toPitchNumber(p: PitchClass): Int = midi.getOrElse(p, 0)
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

  object ScaleDegree extends Enumeration {
    type ScaleDegree = Value
    val I, II, III, IV, V, VI, VII = Value
  } 

  trait Mode extends Music {
    def getDegreePitch(d: ScaleDegree.Value): Pitch
  }

  object IntervalQuality extends Enumeration {
    type IntervalQuality = Value
    val Major, Minor, Perfect, Augmented, Diminished = Value

    val qual = Map("M" -> Major, "maj" -> Major, "m" -> Minor, "min" -> Minor,
      "P" -> Perfect, "aug" -> Augmented, "dim" -> Diminished)

    def apply(s: String): IntervalQuality = qual.getOrElse(s, Major)
    def toString(q: IntervalQuality.Value):String = q match {
      case Major => "M"
      case Minor => "m"
      case Perfect => "P"
      case Augmented => "aug"
      case Diminished => "dim"
    }
  }

  case class Interval(quality: IntervalQuality.Value, number: Int) extends Music {
    //TODO: We also need to test for valid quality+number combos
    require(number != 0)
    override def toString:String = {
      (if(number>0) "+" else "-")+IntervalQuality.toString(quality)+Math.abs(number).toString
    }
  }

  object Interval {
    val r = """^([+,-]?)(M|m|P|aug|dim)(\d+)""".r

    def apply(s: String): Interval = s match {
      case r(q,d) => new Interval(IntervalQuality(q), d.toInt)
      case r(p,q,d) => new Interval(IntervalQuality(q), (p+d).toInt)
    }
  }
}
