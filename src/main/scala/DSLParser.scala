package radical_cadence.dsl

import scala.util.parsing.combinator.RegexParsers
import radical_cadence.dsl.structures._

/**
  * Parser combinator for the music notation DSL.
  */
package object parser {

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
    
    //TODO: Add math for dotted notes
    def note: Parser[Note] = pitchClass ~ opt(pitchDecorator) ~ 
    opt(pitchOctave) ~ """([\d])""".r ^^ {
      case c ~ d ~ o ~ b => Note(Pitch(c, d.getOrElse(PitchDecorator.Blank), 
        o.getOrElse(0)), Beat(1, b.toInt))
    }

    def measure:Parser[MusicContainer]= opt("|") ~> repsep(rep1(note|pitch),"|") <~ opt("|") ^^ {
      case p => Staff(p.map({ Measure(TimeSignature(), _:_*) }):_*)
    }

    def apply(input: String): Music = parseAll(measure, input) match {
        case Success(result, _) => result
        case failure : NoSuccess => scala.sys.error(failure.msg)
    }

  }

  implicit class DSLHelper(val sc: StringContext) extends AnyVal {
    def m(args: Any*): Music = { DSLParser(sc.parts(0)) }
  }
}
