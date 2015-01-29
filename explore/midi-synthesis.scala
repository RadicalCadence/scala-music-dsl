import javax.sound.midi._

object playback extends App {

  def main(args: Array[String]): Unit = {
    val synth = MidiSystem.getSynthesizer()
    synth.open()

    val channel = synth.getChannels().head

    channel.noteOn(32,110)
    //channel.noteOff(32,90)
  }
}
