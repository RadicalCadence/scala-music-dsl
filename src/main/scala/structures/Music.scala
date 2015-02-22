package radical_cadence.dsl

trait Music extends Traversable[Music] {
  def foreach[U](f: Music => U) = f(this)
}
