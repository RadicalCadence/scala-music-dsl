import de.sciss.midi._

val ms  = (64 to 72).flatMap { pch => NoteOn(0, pch, 80) :: NoteOff(0, pch, 0) :: Nil }
implicit val rate = TickRate.tempo(bpm = 120, tpq = 1024)
val ev  = ms.zipWithIndex.map { case (m, i) => Event((i * 0.25 * rate.value).toLong, m) }
val mx  = ev.map(_.tick).max
val t   = Track(ev)
val sq  = Sequence(Vector(t))
val seq = Sequencer.open
seq.play(sq)
