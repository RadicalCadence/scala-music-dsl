package music_dsl

  //TODO: Implement tuplets, maybe staves/voices
object structures {
  sealed trait Music
  case class Beat(value: (Int,Int)) extends Music {
    require((value._2 & (value._2 - 1)) == 0)
  }
  case class Pitch(value: Int) extends Music {
    require(value >= -32 && value <= 32)
  }
  case class Note(pitch: Pitch, duration: Beat) extends Music
  case class PitchSet(pitches: Pitch*) extends Music with Traversable[Pitch] {
    def foreach[U](f: Pitch => U) = pitches foreach f
  }
  case class Chord(pitches: PitchSet, duration: Beat) extends Music
  case class Measure(timeSig: (Int,Int), music: Music*) extends Music with Traversable[Music] {
    def foreach[U](f: Music => U) = music foreach f
  }

  object Pitch {
    val pClass = Map('c' -> 0, 'd' -> 2, 'e' -> 4, 'f' -> 5,
      'g' -> 7, 'a' -> 9, 'b' -> 11)
    val dec = Map('#' -> 1, '-' -> -1, 'x' -> 2, ''' -> 12, ',' -> -12)

    def apply(s:String):Pitch = new Pitch(pClass.getOrElse(s(0).toLower,0)+
        (s.drop(0) collect {case c:Char => dec.getOrElse(c.toLower,0)}).sum
    )
  }

  object PitchSet {
    def apply(s: String): PitchSet = new PitchSet(
      s.split(" ") collect {case p:String => Pitch(p)}:_*
    )
  }
}
