package music_dsl

import scala.util.parsing.combinator.RegexParsers
import music_dsl.structures._

/**
  * Parser combinator for the music notation DSL.
  */
object DSLParser extends RegexParsers {

  def pitchClass: Parser[PitchClass.Value] = """([a-g,A-G])""".r ^^ { 
    PitchClass(_) 
  }
  def pitchDecorator: Parser[PitchDecorator.Value] = """([n|#|x|X|\-|_]?)""".r ^^ {
    PitchDecorator(_) 
  }
  def pitchOctave: Parser[Int] = """([,|']*)""".r ^^ { case s =>
    s.count(c => (c == ''')) - s.count(c => (c == ','))
  }

  def pitch: Parser[Pitch] = pitchClass ~ opt(pitchDecorator) ~ opt(pitchOctave) ^^ {
    case c ~ d ~ o => Pitch(c, d.getOrElse(PitchDecorator.Blank), o.getOrElse(0))
  }

  def measure: Parser[MusicContainer] = opt("|") ~> repsep(rep1(pitch), "|") ^^ {
    case p => new Staff(p.map({ Measure(TimeSignature(), _:_*) }):_*)
  }

  def apply(input: String): Music = parseAll(measure, input) match {
      case Success(result, _) => result
      case failure : NoSuccess => scala.sys.error(failure.msg)
  }

  def main(args: Array[String]): Unit = {
    println(apply("| C# F G# C#' | C# G# F C# |"))
  }
}
