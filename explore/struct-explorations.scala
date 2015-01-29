//TODO: Implement tuplets, maybe staves/voices

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
//-----------------------------------------------
// Lilypond-esque pitch naming
//-----------------------------------------------
val p1 = Pitch(14)
val p2 = Pitch("c##'")
val p3 = Pitch("cx'")

assert((p1 == p2) && (p2 == p3))

//-----------------------------------------------
// Now for something completely different!
//-----------------------------------------------
val pitches = Pitch("c")::Pitch("e")::Pitch("g")::Pitch("c'")::Nil
val beats = Beat(1,4)::Beat(1,4)::Beat(1,4)::Beat(1,4)::Nil

val voice = (pitches zip beats) collect {
  case (p:Pitch,b:Beat) => new Note(p,b)
}

//-----------------------------------------------
// Obviously we can zip lists, we should zip modes!
//-----------------------------------------------
val dorianE = PitchSet("e f# g a b c#' d'")
val voice2 = (dorianE.toList zip beats) collect {
  case (p:Pitch,b:Beat) => new Note(p,b)
}
