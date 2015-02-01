package music_dsl

import scalaz._, Scalaz._

//TODO: Implement tuplets, staves/voices, dynamics
object structures {

  sealed trait Music
  case class Beat(value: (Int,Int) = (1,4)) extends Music {
    require((value._2 & (value._2 - 1)) == 0)
  }
  case class Pitch(pitchClass: PitchClass.PitchClass, 
    decorator: PitchDecorator.PitchDecorator, octave: Int) extends Music 
  case class Note(pitch: Pitch, duration: Beat) extends Music
  case class PitchSet(pitches: Pitch*) extends Music 
  case class Chord(pitches: PitchSet, duration: Beat) extends Music
  case class Measure(timeSig: (Int,Int), music: Music*) extends Music 

  object PitchClass extends Enumeration {
    type PitchClass = Value
    val C, D, E, F, G, A, B = Value

    val pClass = Map("c" -> C, "d" -> D, "e" -> E, "f" -> F,
      "g" -> G, "a" -> A, "b" -> B)
    
    def fromString(s: String):PitchClass = pClass.getOrElse(s.toLowerCase,C)
  }

  object PitchDecorator extends Enumeration {
    type PitchDecorator = Value
    val Blank, Natural, Sharp, Flat, DoubleSharp, DoubleFlat = Value

    val dec = Map("n" -> Natural, "#" -> Sharp, "-" -> Flat, "x" -> DoubleSharp, 
      "##" -> DoubleSharp, "_" -> DoubleFlat)

    def fromString(s: String):PitchDecorator = dec.getOrElse(s.toLowerCase,Blank)
  }

  object Pitch {
    import PitchClass._
    import PitchDecorator._

    val r = """([a-g,A-G])([n|#|##|x|X|-|_]?)([,|']*)""".r

    def apply(s:String): Pitch = s match {
      case r(p) =>     new Pitch(PitchClass.fromString(p),Blank,0)
      case r(p,d) =>   new Pitch(PitchClass.fromString(p),PitchDecorator.fromString(d), 0)
      case r(p,d,o) => new Pitch(PitchClass.fromString(p),PitchDecorator.fromString(d),o.size)
      case _ => new Pitch(C, Blank, 0)
    }
  }

}
