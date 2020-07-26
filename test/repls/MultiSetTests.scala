package repls

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiSetTests extends FunSuite with TimeLimitedTests {
  test("MultiSets can represent the empty Set") {
    assertResult("{}") {
      MultiSet("{}").toString
    }
  }

  test("MultiSets should be generic") {
    assertResult("{a}") {
      MultiSet("{a}").toString
    }

    assertResult("{element}") {
      MultiSet("{element}").toString
    }

    assertResult("{1}") {
      MultiSet("{1}").toString
    }

    assertResult("{1.0}") {
      MultiSet("{1.0}").toString
    }
  }

  test("MultiSets have multiplicity") {
    assertResult("{a,b,c}") {
      MultiSet("{a,b,c}").toString
    }

    assertResult("{a,a,a}") {
      MultiSet("{a,a,a}").toString
    }

    assertResult("{1,1,2,2,3}") {
      MultiSet("{1,1,2,2,3}").toString
    }
  }

  test("MultiSets can be added") {
    val emptySet = MultiSet("{}")

    assertResult("{a}") {
      val singleElement = MultiSet("{a}")
      (emptySet + singleElement).toString
    }

    assertResult("{1,1,2,3}") {
      val longerMultiSet = MultiSet("1,1,2,3")
      (emptySet + longerMultiSet).toString
    }

    assertResult("{1.0,1.0,1.2,1.2,2.0,2.2,4.0}") {
      val first = MultiSet("{1.0,1.2,2.0}")
      val second = MultiSet("{1.0,1.2,2.2,4.0}")
      (first + second).toString
    }

    assertResult("{1,2,2,3,4,5,5}") {
      val first = MultiSet("{1,2}")
      val second = MultiSet("{2,4,5}")
      val third = MultiSet("{3,5}")
      (first + second + third).toString
    }
  }

  test("MultiSets can be subtracted") {
    val emptySet = MultiSet("{}")

    assertResult("{a}") {
      val singleElement = MultiSet("{a}")
      (singleElement - emptySet).toString
    }

    assertResult("{1,1,2,3}") {
      val longerMultiSet = MultiSet("1,1,2,3")
      (longerMultiSet - emptySet).toString
    }

    assertResult("{}") {
      val singleElement = MultiSet("{1}")
      (emptySet - singleElement).toString
    }

    assertResult("{2.0}") {
      val first = MultiSet("{1.0,1.2,2.0}")
      val second = MultiSet("{1.0,1.2,2.2,4.0}")
      (first - second).toString
    }

    assertResult("{1}") {
      val first = MultiSet("{1,2,3}")
      val second = MultiSet("{2,4,5}")
      val third = MultiSet("{3,5}")
      (first - second - third).toString
    }
  }

  test("MultiSets have intersections") {
    val emptySet = MultiSet("{}")

    assertResult("{}") {
      val singleElement = MultiSet("{a}")
      (singleElement * emptySet).toString
    }

    assertResult("{}") {
      val longerMultiSet = MultiSet("1,1,2,3")
      (longerMultiSet * emptySet).toString
    }

    assertResult("{}") {
      val singleElement = MultiSet("{1}")
      (emptySet * singleElement).toString
    }

    assertResult("{1.0,1.2}") {
      val first = MultiSet("{1.0,1.2,2.0}")
      val second = MultiSet("{1.0,1.2,2.2,4.0}")
      (first * second).toString
    }

    assertResult("{2}") {
      val first = MultiSet("{1,2,3}")
      val second = MultiSet("{2,4,5}")
      val third = MultiSet("{2,3,5}")
      (first * second * third).toString
    }

    assertResult("{2,3}") {
      val first = MultiSet("{1,2,2,3,3,4,4,4}")
      val second = MultiSet("{2,3,5,5,5}")
      (first * second).toString
    }
  }

  test("MultiSets can be used in expressions") {
    assertResult("{a,a,d}") {
      val first = MultiSet("{a,a,b,c,d,d,e}")
      val second = MultiSet("{a,b,c,c,c,d,f,g,g}")
      val third = MultiSet("{a,b,g,h,h}")
      val fourth = MultiSet("{a,b,b,c,c,c,d,e,g}")
      (first + second * third - fourth).toString
    }

    assertResult("{2,3}") {
      val first = MultiSet("{1,2,3,4,4,4}")
      val second = MultiSet("{1,4,5,5,5}")
      val third = MultiSet("{1,1,1,2,3,3,4,5}")
      val fourth = MultiSet("{1,4,6,7,7,8}")
      ((first - second) * (third - fourth)).toString
    }

    assertResult("{d,d,i,o}") {
      val first = MultiSet("{d,d,d,g,h,i,i,i,o}")
      val second = MultiSet("{a,b,d,g,i,i}")
      val third = MultiSet("{d,d,d,h,i,l,o,o}")
      val fourth = MultiSet("{d,d,i,i,o,o,p,p}")
      ((first - second) * third * fourth).toString
    }
  }

  override def timeLimit: Span = Span(1, Seconds)
  override val defaultTestSignaler: Signaler = ReallyStopSignaler
}
