package music_dsl

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

/**
  * Parser combinator for the music notation DSL.
  */
object parser extends StandardTokenParsers {

  /* Below is the current EBNF grammar for our parser:
   *
   * note ::= ( named_pitch | scale_degree ) ["#" | "-"] [beat_duration]
   *
   * named_pitch ::= ( "a" | "b" | "c" | "d" | "e" | "f" | "g" )
   *
   * scale_degree ::= ( "i" | "ii" | "iii" | "iv" | "v" | "vi" | "vii" )
   *
   * beat_duration ::= numericLit [ "." | "~" ]
   */

  lexical.reserved += ("@title", "@author", "@key", "@mode", "@timeSignature", "@coda", "@codads")
  lexical.delimiters += ("(", ")", "|", "|:", ":|", "||", "=")

  // Parser Code Here

  def parse(in: String): ParseResult[Expr] = parseAll(pcell,in) 
  def parseAll[T](p: Parser[T], in: String): ParseResult[T] =
      phrase(p)(new lexical.Scanner(in))
}
