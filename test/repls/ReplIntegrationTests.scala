// DO NOT MODIFY THIS FILE
package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import repls.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class ReplIntegrationTests extends TestBase {
  /*
  Common errors
   */
  test("No valid variable name", weight = 4) {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("15 = 1")
    }
  }

  test("Invalid type") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("42 + {a,a,b}")
    }
  }

  test("Invalid type assigning") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("n = {a,b,c,c}")
    }
  }

  test("Not known command", weight = 2) {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("! 1 * 4")
    }
  }

  test("Assignment without value", weight = 2) {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("a = ")
    }
  }

  test("Simplification without expression", weight = 2) {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[Error] {
      repl.readEval("@ ")
    }
  }
}

