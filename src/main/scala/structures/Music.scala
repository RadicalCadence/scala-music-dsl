package radical_cadence.dsl

trait Music extends Traversable[Music] {
  def foreach[U](f: Music => U) = f(this)

  /** Format this Music object as a LilyPond string. */
  def asLy: String

  /** Format this Music object as a DSL string. */
  def asDSL: String
}
