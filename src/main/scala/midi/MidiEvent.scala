package music_dsl

object MidiEvent {
  import javax.sound.midi.MidiMessage

  def apply(msg: MidiMessage, tick: Long) = {
    new javax.sound.midi.MidiEvent(msg, tick)
  }

  def unapply(event: javax.sound.midi.MidiEvent) = {
    Some(event.getMessage, event.getTick)
  }
}

object NoteOn {

  import javax.sound.midi.ShortMessage

  def apply(channel: Int = 0, key: Int, velocity: Int = 100) = {
    new ShortMessage(javax.sound.midi.ShortMessage.NOTE_ON, channel, key, velocity)
  }

  def unapply(msg: javax.sound.midi.ShortMessage) = {
    if (msg.getCommand == javax.sound.midi.ShortMessage.NOTE_ON) {
      Some(msg.getChannel, msg.getData1, msg.getData2)
    } else None
  }
}

object NoteOff {

  import javax.sound.midi.ShortMessage

  def apply(channel: Int = 0, key: Int, velocity: Int = 100) = {
    new ShortMessage(javax.sound.midi.ShortMessage.NOTE_OFF, channel, key, velocity)
  }
}

