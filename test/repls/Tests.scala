package repls

import org.junit.runner.RunWith
import org.scalactic.Tolerance._
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Tests extends FunSuite with TimeLimitedTests {
  test("echo") {
    val repl = REPLFactory.makeIntREPL()

    assertResult("1") {
      repl.readEval("1")
    }
  }

  test("calc") {
    val repl = REPLFactory.makeIntREPL()

    assertResult("21") {
      repl.readEval("1 + 4 * 5")
    }
  }

  test("assign") {
    val repl = REPLFactory.makeIntREPL()

    assertResult("21") {
      repl.readEval("1 + 4 * 5")
    }
  }

  override def timeLimit: Span = Span(1,Seconds)
  // this is need to actually stop when the buggy code contains an infinite loop...
  override val defaultTestSignaler: Signaler = ReallyStopSignaler
}

object ReallyStopSignaler extends Signaler {
  override def apply(testThread: Thread): Unit = {
    StopRunningNow.stopRunningNowUnsafe(testThread)
  }
}
