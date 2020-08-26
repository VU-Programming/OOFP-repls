// DO NOT MODIFY THIS FILE
package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import repls.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class ReplTests extends TestBase {
  /*
  IntRepl errors
   */
  test("Invalid expression") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("1 +")
    }
  }

  test("Open brackets right") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("( 1 + 3")
    }
  }

  test("Invalid brackets left") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval(" 5 * 4 )")
    }
  }

  test("Unknown operator") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("10 / 5")
    }
  }

  test("No known variable") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("a = 10")
      repl.readEval("a + b")
    }
  }

  test("Assignment with unknown variable") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("a = b + 3")
    }
  }

  test("Invalid simplification") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("@ a +")
    }
  }

  test("Invalid simplification open bracket right") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("@ ( a +")
    }
  }

  test("Invalid simplification open bracket left") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("@ a + )")
    }
  }

  /*
  MultisetRepl errors
  */
  test("Invalid multiset") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[Error] {
      repl.readEval("{a,b")
    }
  }

  test("Invalid multiset expression") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("{a,b} +")
    }
  }

  test("Open brackets right multiset") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval("( {a,c} + {a,b}")
    }
  }


  test("Invalid brackets left multiset") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[NoSuchElementException] {
      repl.readEval(" {a,c} - {a,b} )")
    }
  }

  test("Unknown operator multiset") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[Error] {
      repl.readEval("{a,a,b} > {a,b}")
    }
  }

  test("No known multiset variable") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[Error] {
      repl.readEval("a = {a,a,b,b,c}")
      repl.readEval("a + b")
    }
  }

  /*
  Common errors
   */
  test("No valid variable name") {
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

  test("Not known command") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("! 1 * 4")
    }
  }

  test("Assignment without value") {
    val repl = REPLFactory.makeIntREPL()

    assertThrows[Error] {
      repl.readEval("a = ")
    }
  }

  test("Simplification without expression") {
    val repl = REPLFactory.makeMultiSetREPL()

    assertThrows[Error] {
      repl.readEval("@ ")
    }
  }
}

