package repls

/*
    Multiset is a Map of elements and their respective count.
    For example:
    {a,a,a,b,c,c} = Map('a'->3, 'b'->1, 'c'->2)
 */

case class MultiSet[T](elements: Map[T, Int]) {

    /* TODO
        Intersection of two multisets:
        ∀x m_c(x) = min(m_a(x), m_b(x))
        Example:
        {a,b,b,c,c,c} * {b,c,c,c,c} = {b,c,c,c}
     */
    def *(that: MultiSet[T]): MultiSet[T] = {
        _
    }

    /* TODO
        Summation of two multisets:
        ∀x m_c(x) = m_a(x) + m_b(x)
        Example:
        {a,b,c,c} + {a,c,d} = {a,a,b,c,c,c,d}
     */
    def +(that: MultiSet[T]): MultiSet[T] = {
        _
    }

    /* TODO
        Subtraction of two multisets:
        ∀x m_c(x) = max(m_a(x) - m_b(x), 0)
        Example:
        {a,b,b,d} - {b,c,c,d,d} = {a,b}
     */
    def -(that: MultiSet[T]): MultiSet[T] = {
        _
    }

    /* TODO
        Make sure a multiset can be returned as a sequence.
     */
    def toSeq: Seq[T] = {
        Seq.empty
    }

    /* TODO
        Have a suitable string representation. Make sure it follows the style we use in the examples.
     */
    override def toString: String = {
        ""
    }
}

object MultiSet {
    /* TODO
        Write a constructor that constructs a multiset from a sequence of elements
     */
    def apply[T](elements: Seq[T]): MultiSet[T] = {
        _
    }
}
