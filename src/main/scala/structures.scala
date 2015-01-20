object structures {
  sealed trait Music
  case class Beat(value: (Int,Int)) extends Music
  case class Pitch(value: Int) extends Music
  case class Note(pitch: Pitch, duration: Beat) extends Music
  case class PitchSet(pitches: Pitch*) extends Music
  case class Chord(duration: Beat, pitches: Pitch*) extends Music
  //TODO: Implement measures, tuplets, maybe staves/voices
}
