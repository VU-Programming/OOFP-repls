// DO NOT MODIFY THIS FILE
package repls

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import repls.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class IntReplTests extends TestBase {
    /*
     Basic parsing and echoing
      */
    test("Echo simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("1")
        val expected = "1"

        assert(result == expected)
    }

    test("Echo brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( 42 )")
        val expected = "42"

        assert(result == expected)
    }

    /*
    Evaluating with addition
     */
    test("Evaluate addition simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("1178 + 465 + 65 + 204 + 444")
        val expected = "2356"

        assert(result == expected)
    }

    test("Evaluate addition brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( 12 + 1 ) + ( 45 + 547 + 56 )")
        val expected = "661"

        assert(result == expected)
    }

    test("Evaluate addition negative numbers", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("10 + -11")
        val expected = "-1"

        assert(result == expected)
    }

    /*
    Evaluating with subtraction
     */
    test("Evaluate subtraction simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("5 - 2")
        val expected = "3"

        assert(result == expected)
    }

    test("Evaluate subtraction brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("24 - ( 75 - 643 )")
        val expected = "592"

        assert(result == expected)
    }

    test("Evaluate subtraction negative") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( 0 - 29 )")
        val expected = "-29"

        assert(result == expected)
    }

    test("Evaluate subtraction two negatives make positives", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("3 - -5")
        val expected = "8"

        assert(result == expected)
    }

    /*
    Evaluating with multiplication
     */
    test("Evaluate multiplication simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("2 * 4")
        val expected = "8"

        assert(result == expected)
    }

    test("Evaluate multiplication brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( 23 * 123 ) * ( 2 * 32 )")
        val expected = "181056"

        assert(result == expected)
    }

    test("Evaluate multiplication negative", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( -10 * 64 ) * ( 2 * 32 )")
        val expected = "-40960"

        assert(result == expected)
    }

    /*
    Evaluation expressions with different operations
     */
    test("Evaluate Expressions simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("1 + 4 * 5")
        val expected = "21"

        assert(result == expected)
    }

    test("Evaluate Expressions brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("5 * ( 45 + 1247 * 87 ) + 583")
        val expected = "543253"

        assert(result == expected)
    }

    test("Evaluate Expressions nested brackets", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("( 43 - ( 35 * 2 + ( 32 - ( 45 ) * 2 ) ) )")
        val expected = "31"

        assert(result == expected)
    }

    test("Evaluate Expressions advanced brackets", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("35 + ( 35 * ( ( 54 - 465 ) + ( ( 35 ) - 3 ) ) - ( 43 + 61 ) ) * 1")
        val expected = "-13334"

        assert(result == expected)
    }

    /*
    Assigning variables
     */
    test("Assign variables simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("n = 1")
        val expected = "n = 1"

        assert(result == expected)
    }

    test("Assign variables and evaluate") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("n = 1 + 4 * 5")
        val expected = "n = 21"

        assert(result == expected)
    }

    test("Assign variables reuse") {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("n = 21")
        val result = repl.readEval("m = n + 4")
        val expected = "m = 25"

        assert(result == expected)
    }

    test("Assign variables negative numbers", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("n = -16")
        val result = repl.readEval("n = n + 4")
        val expected = "n = -12"

        assert(result == expected)
    }

    test("Assign variables reassign") {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("p = 12")
        val result = repl.readEval("p = ( 1 + p * 2 ) * 2 + p")
        val expected = "p = 62"

        assert(result == expected)
    }

    test("Assign variables evaluate") {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("q = 12")
        val result = repl.readEval("5 + q")
        val expected = "17"

        assert(result == expected)
    }

    test("Assign variables advanced", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("r = 4 * 12")
        repl.readEval("r = ( r * 2 - 45 ) + 126")

        val result = repl.readEval("45 + ( r * r ) - ( 2 + 16 )")
        val expected = "31356"

        assert(result == expected)
    }

    /*
    Simplification with the basic rules and constant values
     */

    test("Simplify simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 4 * 4")
        val expected = "16"

        assert(result == expected)
    }

    test("Simplify unneeded 0 left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 0 + 13")
        val expected = "13"

        assert(result == expected)
    }

    test("Simplify unneeded 0 right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 55 + 0")
        val expected = "55"

        assert(result == expected)
    }

    test("Simplify multiply by one left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 1 * 3")
        val expected = "3"

        assert(result == expected)
    }

    test("Simplify multiply by one right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 3 * 1")
        val expected = "3"

        assert(result == expected)
    }

    test("Simplify multiply by 0 left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 0 * 3")
        val expected = "0"

        assert(result == expected)
    }

    test("Simplify multiply by 0 right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 3 * 0")
        val expected = "0"

        assert(result == expected)
    }

    test("Simplify brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ ( 4 * 0 ) + ( 4 * 2 )")
        val expected = "8"

        assert(result == expected)
    }

    test("Simplify negative numbers") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ ( 22 * -11 ) + ( 7 * 6 )")
        val expected = "-200"

        assert(result == expected)
    }

    test("Simplify trailing 0", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ ( 34 + 6 ) * 2  * 0")
        val expected = "0"

        assert(result == expected)
    }

    test("Simplify leading 0", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 0 * ( 34 + 6 )")
        val expected = "0"

        assert(result == expected)
    }
    test("Simplify advanced", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ ( ( 23 * 2 ) + ( 2 * 9 ) ) + 34 + 4 * 1")
        val expected = "102"

        assert(result == expected)
    }

    /*
    Simplification with abstract variables
     */
    test("Simplify abstract simple") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a + 5")
        val expected = "a + 5"

        assert(result == expected)
    }

    test("Simplify unneeded 0 on abstract left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 0 + a")
        val expected = "a"

        assert(result == expected)
    }

    test("Simplify unneeded 0 on abstract right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a + 0")
        val expected = "a"

        assert(result == expected)
    }

    test("Simplify multiply by 1 on abstract left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 1 * a")
        val expected = "a"

        assert(result == expected)
    }

    test("Simplify multiply by 1 on abstract right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a * 1")
        val expected = "a"

        assert(result == expected)
    }

    test("Simplify multiply by 0 on abstract left") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ 0 * a")
        val expected = "0"

        assert(result == expected)
    }

    test("Simplify multiply by 0 on abstract right") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a * 0")
        val expected = "0"

        assert(result == expected)
    }

    test("Simplify abstract brackets") {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a + ( b * 1 )")
        val expected = "a + b"

        assert(result == expected)
    }

    test("Simplify abstract advanced", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        val result = repl.readEval("@ a + ( ( b * c ) + ( c * d ) )")
        val expected = "a + c * ( b + d )"

        assert(result == expected)
    }

    /*
    Simplification with assigned variables
     */
    test("Simplify with assigned variables") {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("a = -15")
        val result = repl.readEval("@ a")
        val expected = "-15"

        assert(result == expected)
    }


    test("Simplify with assigned variables brackets", weight = 2) {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("a = -10")
        val result = repl.readEval("@ ( 3 + ( 4 * a ) ) * b")
        val expected = "-37 * b"

        assert(result == expected)
    }

    test("Simplify with assigned variables advanced", weight = 5) {
        val repl = REPLFactory.makeIntREPL()

        repl.readEval("a = 3")
        repl.readEval("b = a * 4")
        repl.readEval("c = b + ( a * 5 )")

        val result = repl.readEval("@ ( ( ( a * b ) + ( c * b ) ) * 2 ) + d")
        val expected = "720 + d"

        assert(result == expected)
    }
}
