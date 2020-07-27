package repls

case class MultiSet[T]() {

    def *(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    def +(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    def -(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    def toSeq: Seq[T] = {
        Seq.empty
    }

    override def toString: String = {
        ""
    }
}

object MultiSet {
    def apply[T](elements: Seq[T]): MultiSet[T] = {
        MultiSet()
    }
}
