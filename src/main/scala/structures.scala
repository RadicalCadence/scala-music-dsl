package music_dsl

import scalaz._, Scalaz._

object structures {

  trait Music

  /** MusicLeaf is a leaf class in the Music tree, representing
   *  pitches, rhythms, notes, and chords.
   */
  sealed trait MusicLeaf extends Music
  case class Beat(num: Int = 1, denom: Int = 4) extends MusicLeaf {
    require((denom & (denom - 1)) == 0, "Beat denominator must be a power of two.")
  }
  case class TimeSignature(num: Int = 4, denom: Int = 4) extends Music
  case class NamedPitch(pitchClass: PitchClass.Value, 
    decorator: PitchDecorator.Value, octave: Int) extends MusicLeaf {
    def toPitchNumber: Int = PitchClass.toPitchNumber(pitchClass)+
     PitchDecorator.toPitchNumber(decorator) + (octave*12) 
  }
  case class Note(pitch: NamedPitch, duration: Beat) extends MusicLeaf
  
  /** MusicContainers hold MusicLeave classes */
  //TODO: Implement dynamics
  sealed trait MusicContainer extends Music
  case class Measure(timeSignature: TimeSignature, music: MusicLeaf*) extends MusicContainer
  case class Staff(music: Music*) extends MusicContainer

  object PitchClass extends Enumeration {
    type PitchClass = Value
    val C, D, E, F, G, A, B = Value

    private val pClass = Map("c" -> C, "d" -> D, "e" -> E, "f" -> F,
      "g" -> G, "a" -> A, "b" -> B)
    private val midi = Map(C -> 0, D -> 2, E -> 4, F -> 5, G -> 7,
      A -> 9, B -> 11)
    
    def apply(s: String):PitchClass = pClass.getOrElse(s.toLowerCase, C)
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
  }

  object NamedPitch {
    import PitchClass._
    import PitchDecorator._

    private val r = """([a-g,A-G])([n|#|x|X|\-|_]?)([,|']*)""".r

    private val midi = Map(0 -> "C", 1 -> "C#", 2 -> "D", 3 -> "E-",
      4 -> "E", 5 -> "F", 6 -> "F#", 7 -> "G", 8 -> "A-", 9 -> "A",
      10 -> "B-", 11 -> "B")

    def apply(s:String): NamedPitch = s match {
      case r(p) =>     new NamedPitch(PitchClass(p),Blank,0)
      case r(p,d) =>   new NamedPitch(PitchClass(p),PitchDecorator(d), 0)
      case r(p,d,o) => new NamedPitch(PitchClass(p),PitchDecorator(d),
        o.count(c => (c == ''')) - o.count(c => (c == ',')))
      case _ => ???
    }
    def apply(i: Int): NamedPitch = { 
      var octaves = "";
      if(i > 0) {
        octaves = "'" * (i / 12)
      } else {
        octaves = "," * (Math.abs(i-12) / 12)
      }

      NamedPitch((midi.getOrElse(Math.abs((i+48)%12),"C")+octaves))
    }
  }
  
}
