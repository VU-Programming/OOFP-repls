package repls

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiSetTests extends FunSuite with TimeLimitedTests {
  test("Empty set") {
    assertResult("{}") {
      MultiSet("{}").toString
    }
  }

  test("Single element, generic types") {
    assertResult("{a}") {
      MultiSet[Char]("{a}").toString
    }

    assertResult("{1}") {
      MultiSet[Int]("{1}").toString
    }

    assertResult("{1.0}") {
      MultiSet[Float]("{1.0}").toString
    }
  }

  test("Empty add one") {
    val msEmpty = MultiSet[Int]()
    val msOne = MultiSet[Int]("{a}")

    assertResult("{a}") {
      (msEmpty + msOne).toString
    }
  }

//  (multiset.toString, "addMember", "{a}", multiset.addMember("a").toString, "{a}")
//  (multiset.toString, "addMember", "{a,a,a}", multiset.addMember("a", 3).toString, "{a,a,a}")
//  (multiset.toString, "removeMember", "{a}", multiset.removeMember("a").toString, "{}")
//  (multiset.toString, "removeMember", "{a,a,a}", multiset.removeMember("a", 3).toString, "{}")
//
//  val testMultiSet0 = MultiSet(Map("a" -> 1, "b" -> 2, "c" -> 3))
//  val testMultiSet1 = MultiSet(Map("a" -> 3, "b" -> 1, "c" -> 2))
//  (testMultiSet0.toString, "+", testMultiSet1.toString, (testMultiSet0 + testMultiSet1).toString, "{a,a,a,a,b,b,b,c,c,c,c,c}")
//  (testMultiSet0.toString, "-", testMultiSet1.toString, (testMultiSet0 - testMultiSet1).toString, "{b,c}")
//  (testMultiSet1.toString, "-", testMultiSet0.toString, (testMultiSet1 - testMultiSet0).toString, "{a,a}")
//  (testMultiSet0.toString, "*", testMultiSet1.toString, (testMultiSet0 * testMultiSet1).toString, "{a,b,c,c}")
//
//  ("{a,b,b,c,c,c}", "apply", "nothing", MultiSet("{a,b,b,c,c,c}").toString, "{a,b,b,c,c,c}")
//  ("{}", "apply", "nothing", MultiSet("{}").toString, "{}")

  override def timeLimit: Span = Span(1, Seconds)
  override val defaultTestSignaler: Signaler = ReallyStopSignaler
}
