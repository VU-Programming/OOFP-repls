package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntReplTests extends TestsBase {
    test("The IntRepl should echo resolved expressions") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("1") {
            repl.readEval("1")
        }

        assertResult("22") {
            repl.readEval("22")
        }

        assertResult("3578") {
            repl.readEval("3578")
        }

        assertResult("84") {
            repl.readEval("(84)")
        }
    }

    test("The IntRepl should evaluate additions") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("5") {
            repl.readEval("4 + 1")
        }

        assertResult("24") {
            repl.readEval("4 + 13 + 7")
        }

        assertResult("2356") {
            repl.readEval("1178 + 465 + 65 + 204 + 444")
        }

        assertResult("50") {
            repl.readEval("24 + (14 + 12)")
        }

        assertResult("661") {
            repl.readEval("(12 + 1) + (45 + 547 + 56)")
        }
    }

    test("The IntRepl should evaluate subtractions") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("3") {
            repl.readEval("5 - 2")
        }

        assertResult("31") {
            repl.readEval("65 - 34")
        }

        assertResult("26304") {
            repl.readEval("32478 - 3478 - 2347 - 4 - 345")
        }

        assertResult("-1") {
            repl.readEval("-1")
        }

        assertResult("-29") {
            repl.readEval("(-29)")
        }

        assertResult("-694") {
            repl.readEval("24 - (75 - 643)")
        }

        assertResult("-1533") {
            repl.readEval("(12 - 45) - (1245 - 255)")
        }
    }

    test("The IntRepl should evaluate multiplications") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("8") {
            repl.readEval("2 * 4")
        }

        assertResult("72") {
            repl.readEval("24 * 3")
        }

        assertResult("2373540") {
            repl.readEval("34 * 65 * 3 * 358")
        }

        assertResult("2160") {
            repl.readEval("45 * (12 * 4)")
        }

        assertResult("181056") {
            repl.readEval("(23 * 123) * (2 * 32)")
        }
    }

    test("The IntRepl should evaluate expressions") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("21") {
            repl.readEval("1 + 4 * 5")
        }

        assertResult("11") {
            repl.readEval("3 * 2 + 5")
        }

        assertResult("106") {
            repl.readEval("10 + 8 * 12")
        }

        assertResult("612") {
            repl.readEval("102 * (4 + 2)")
        }

        assertResult("87") {
            repl.readEval("9 - 45 + 123")
        }

        assertResult("543253") {
            repl.readEval("5 * (45 + 1247 * 87) + 583")
        }

        assertResult("-85") {
            repl.readEval("(43 - 35 * 2 + (32 - (45) * 2))")
        }

        assertResult("-13334") {
            repl.readEval("35 + (35 * (54 - 465 + (35) - 3) - (43 + 61)) * 1")
        }
    }

    test("The IntRepl should be able to assign and use variables") {
        val repl = REPLFactory.makeIntREPL()

        assertResult("n = 1") {
            repl.readEval("n = 1")
        }

        assertResult("n = 21") {
            repl.readEval("n = 1 + 4 * 5")
        }

        assertResult("m = 25") {
            repl.readEval("m = 21")
            repl.readEval("m = m + 4")
        }

        assertResult("p = 62") {
            repl.readEval("p = 12")
            repl.readEval("p = (1 + p * 2) * 2 + p")
        }

        assertResult("17") {
            repl.readEval("q = 12")
            repl.readEval("5 + q")
        }

        assertResult("31388") {
            repl.readEval("r = 4 * 12")
            repl.readEval("r = r * 2 - 45 + 126")
            repl.readEval("45 + (r * r) - (2 + 16)")
        }
    }
}
