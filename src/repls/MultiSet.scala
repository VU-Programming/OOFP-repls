package repls

case class MultiSet[T]() {
    def *(that: MultiSet[T]): MultiSet[T] = {
        MultiSet(Iterable.empty)
    }

    def +(that: MultiSet[T]): MultiSet[T] = {
        MultiSet(Iterable.empty)
    }

    def -(that: MultiSet[T]): MultiSet[T] = {
        MultiSet(Iterable.empty)
    }

    def asIterable: Iterable[T] = {
        Iterable.empty
    }

    override def toString: String = {
        ""
    }
}

object MultiSet {
    def apply[T](elements: Iterable[T]): MultiSet[T] = {
        MultiSet()
    }
}
