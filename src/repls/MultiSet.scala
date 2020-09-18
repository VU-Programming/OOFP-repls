package repls

case class MultiSet[T]() {

    /*
    Intersection of two multisets:
    ∀x m_c(x) = min(m_a(x), m_b(x))

    Example:
    {a,b,b,c,c,c} * {b,c,c,c,c} = {b,c,c,c}
     */
    def *(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    /*
    Summation of two multisets:
    ∀x m_c(x) = m_a(x) + m_b(x)

    Example:
    {a,b,c,c} + {a,c,d} = {a,a,b,c,c,c,d}
     */
    def +(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    /*
    Subtraction of two multisets:
    ∀x m_c(x) = max(m_a(x) - m_b(x), 0)

    Example:
    {a,b,b,d} - {b,c,c,d,d} = {a,b}
     */
    def -(that: MultiSet[T]): MultiSet[T] = {
        MultiSet()
    }

    /*
    Make sure a multiset can be returned as a sequence.
     */
    def toSeq: Seq[T] = {
        Seq.empty
    }

    /*
    Have a suitable string representation. Make sure it follows the style we use in the examples.
     */
    override def toString: String = {
        ""
    }
}

object MultiSet {
    /*
    Construct a multiset from a sequence of elements.
     */
    def apply[T](elements: Seq[T]): MultiSet[T] = {
        MultiSet()
    }
}
