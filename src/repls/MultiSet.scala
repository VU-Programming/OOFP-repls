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

    override def toString: String = {
        ""
    }
}

object MultiSet {
    def apply[T](input: String): MultiSet[T] = {
        MultiSet()
    }
}
