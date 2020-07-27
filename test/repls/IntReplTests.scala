package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntReplTests extends TestsBase {
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
}
