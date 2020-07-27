package repls

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiSetTests extends FunSuite with TimeLimitedTests {
  test("MultiSets can represent the empty Set") {
    assertResult(Iterable.empty) {
      MultiSet(Iterable.empty).toSeq
    }
  }

  test("MultiSets should be generic") {
    assertResult(Iterable('a')) {
      MultiSet(Iterable('a')).toSeq
    }

    assertResult(Iterable("element")) {
      MultiSet(Iterable("element")).toSeq
    }

    assertResult(Iterable(1)) {
      MultiSet(Iterable(1)).toSeq
    }

    assertResult(Iterable(1.0)) {
      MultiSet(Iterable(1.0)).toSeq
    }
  }

  test("MultiSets have multiplicity") {
    assertResult(Iterable('a','b','c')) {
      MultiSet(Iterable('a','b','c')).toSeq.sorted
    }

    assertResult(Iterable('a','a','a')) {
      MultiSet(Iterable('a','a','a')).toSeq.sorted
    }

    assertResult(Iterable(1,1,2,2,3)) {
      MultiSet(Iterable(1,1,2,2,3)).toSeq.sorted
    }
  }

  test("MultiSets have the correct string representation") {
    assertResult("{}") {
      MultiSet(Iterable.empty).toString
    }

    assertResult("{1,1,2}") {
      MultiSet(Iterable(1,1,2)).toString
    }

    assertResult("{a,a,a,b,c}") {
      MultiSet(Iterable('a','b','c','a','a')).toString
    }
  }

  test("MultiSets can be added") {
    assertResult(Iterable('a')) {
      val singleElement = MultiSet(Iterable('a'))
      val emptySet = MultiSet[Char](Iterable.empty)

      (emptySet + singleElement).toSeq.sorted
    }

    assertResult(Iterable(1,1,2,3)) {
      val emptySet = MultiSet[Int](Iterable.empty)
      val longerMultiSet = MultiSet(Iterable(1,1,2,3))

      (emptySet + longerMultiSet).toSeq.sorted
    }

    assertResult(Iterable(1.0,1.0,1.2,1.2,2.0,2.2,4.0)) {
      val first = MultiSet(Iterable(1.0,1.2,2.0))
      val second = MultiSet(Iterable(1.0,1.2,2.2,4.0))

      (first + second).toSeq.sorted(Ordering.Double.TotalOrdering)
    }

    assertResult(Iterable(1,2,2,3,4,5,5)) {
      val first = MultiSet(Iterable(1,2))
      val second = MultiSet(Iterable(2,4,5))
      val third = MultiSet(Iterable(3,5))

      (first + second + third).toSeq.sorted
    }
  }

  test("MultiSets can be subtracted") {
    assertResult(Iterable('a')) {
      val singleElement = MultiSet(Iterable('a'))
      val emptySet = MultiSet[Char](Iterable.empty)

      (singleElement - emptySet).toSeq.sorted
    }

    assertResult(Iterable(1,1,2,3)) {
      val longerMultiSet = MultiSet(Iterable(1,1,2,3))
      val emptySet = MultiSet[Int](Iterable.empty)

      (longerMultiSet - emptySet).toSeq.sorted
    }

    assertResult(Iterable.empty) {
      val emptySet = MultiSet[Int](Iterable.empty)
      val singleElement = MultiSet(Iterable(1))

      (emptySet - singleElement).toSeq.sorted
    }

    assertResult(Iterable(2.0)) {
      val first = MultiSet(Iterable(1.0,1.2,2.0))
      val second = MultiSet(Iterable(1.0,1.2,2.2,4.0))

      (first - second).toSeq.sorted(Ordering.Double.TotalOrdering)
    }

    assertResult(Iterable(1)) {
      val first = MultiSet(Iterable(1,2,3))
      val second = MultiSet(Iterable(2,4,5))
      val third = MultiSet(Iterable(3,5))

      (first - second - third).toSeq.sorted
    }
  }

  test("MultiSets have intersections") {
    assertResult(Iterable.empty) {
      val singleElement = MultiSet(Iterable('a'))
      val emptySet = MultiSet[Char](Iterable.empty)

      (singleElement * emptySet).toSeq.sorted
    }

    assertResult(Iterable.empty) {
      val longerMultiSet = MultiSet(Iterable(1,1,2,3))
      val emptySet = MultiSet[Int](Iterable.empty)

      (longerMultiSet * emptySet).toSeq.sorted
    }

    assertResult(Iterable.empty) {
      val emptySet = MultiSet[Int](Iterable.empty)
      val singleElement = MultiSet(Iterable(1))

      (emptySet * singleElement).toSeq.sorted
    }

    assertResult(Iterable(1.0,1.2)) {
      val first = MultiSet(Iterable(1.0,1.2,2.0))
      val second = MultiSet(Iterable(1.0,1.2,2.2,4.0))

      (first * second).toSeq.sorted(Ordering.Double.TotalOrdering)
    }

    assertResult(Iterable(2)) {
      val first = MultiSet(Iterable(1,2,3))
      val second = MultiSet(Iterable(2,4,5))
      val third = MultiSet(Iterable(2,3,5))

      (first * second * third).toSeq.sorted
    }

    assertResult(Iterable(2,3)) {
      val first = MultiSet(Iterable(1,2,2,3,3,4,4,4))
      val second = MultiSet(Iterable(2,3,5,5,5))

      (first * second).toSeq.sorted
    }
  }

  test("MultiSets can be used in expressions") {
    assertResult(Iterable('a','a','d')) {
      val first = MultiSet(Iterable('a','a','b','c','d','d','e'))
      val second = MultiSet(Iterable('a','b','c','c','c','d','f','g','g'))
      val third = MultiSet(Iterable('a','b','g','h','h'))
      val fourth = MultiSet(Iterable('a','b','b','c','c','c','d','e','g'))

      (first + second * third - fourth).toSeq.sorted
    }

    assertResult(Iterable(2,3)) {
      val first = MultiSet(Iterable(1,2,3,4,4,4))
      val second = MultiSet(Iterable(1,4,5,5,5))
      val third = MultiSet(Iterable(1,1,1,2,3,3,4,5))
      val fourth = MultiSet(Iterable(1,4,6,7,7,8))

      ((first - second) * (third - fourth)).toSeq.sorted
    }

    assertResult(Iterable('d','d','i','o')) {
      val first = MultiSet(Iterable('d','d','d','g','h','i','i','i','o'))
      val second = MultiSet(Iterable('a','b','d','g','i','i'))
      val third = MultiSet(Iterable('d','d','d','h','i','l','o','o'))
      val fourth = MultiSet(Iterable('d','d','i','i','o','o','p','p'))

      ((first - second) * third * fourth).toSeq.sorted
    }
  }

  override def timeLimit: Span = Span(1, Seconds)
  override val defaultTestSignaler: Signaler = ReallyStopSignaler
}
