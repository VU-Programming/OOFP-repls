package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import repls.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class IntReplIntegrationTests extends TestBase {
    /*
    IntRepl errors
     */
    test("Invalid expression", weight = 2) {
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

    test("Invalid simplification", weight = 2) {
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
}
