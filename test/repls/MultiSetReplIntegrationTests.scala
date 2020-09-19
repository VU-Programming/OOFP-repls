package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import repls.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class MultiSetReplIntegrationTests extends TestBase {
    /*
    MultisetRepl errors
    */
    test("Invalid multiset", weight = 2) {
        val repl = REPLFactory.makeMultiSetREPL()

        assertThrows[Error] {
            repl.readEval("{a,b")
        }
    }

    test("Invalid multiset expression", weight = 2) {
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
}
