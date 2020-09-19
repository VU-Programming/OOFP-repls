// DO NOT MODIFY THIS FILE
package repls.infrastructure

import java.io.{OutputStream, PrintStream}

import org.scalatest.{Args, ConfigMap, Reporter}
import org.scalatest.events.{Event, TestFailed, TestSucceeded}
import repls.{AllTests, ReplsTestSuite4_1, ReplsTestSuite4_2, ReplsTestSuitesBase}

class CustomReporter(val out : PrintStream) extends Reporter {
    override def apply(event: Event): Unit = {
        event match {
            case e : TestSucceeded => out.printf("%-20s succeeded!\n",e.testName)
            case e : TestFailed =>  out.printf("%-20s FAILED!\n",e.testName)
            case _ => ()
        }
    }
}

// reports your score as a fraction between 0 and 1 for codegrade
abstract class ReportFraction {
    def Tests(): ReplsTestSuitesBase

    def runGetScoreCounter(): ScoreCounter = {
        val out = System.out
        // prevent inventive students from printing 1.0 to
        // stderr and then getting full points
        System.setOut(new PrintStream(new OutputStream {
            override def write(i: Int): Unit = ()
        }))
        System.setErr(new PrintStream(new OutputStream {
            override def write(i: Int): Unit = ()
        }))
        val scoreCounter = new ScoreCounter()
        Tests().runDirect(None, Args(
            reporter = new CustomReporter(out),
            configMap = ConfigMap("scoreCounter" -> scoreCounter))
        )
        scoreCounter
    }
}

object ReportFraction4_1 extends ReportFraction {
    override def Tests() = new ReplsTestSuite4_1()

    def main(args: Array[String]): Unit = {
        val out = System.out;
        val scoreCounter = runGetScoreCounter()

        out.printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        if(scoreCounter.points >=Tests.MinPointsToPass ) out.printf("You passed exercise 4.1\n")
        else out.printf("You did not pass exercise 4.1 yet\n")
        val frac = if(scoreCounter.points >= Tests.MinPointsToPass) 1.0 else 0
        out.printf("Fractiontouseforcodegrade %.2f",frac)
    }
}

object ReportFraction4_2 extends ReportFraction {
    override def Tests() = new ReplsTestSuite4_2()

    def main(args: Array[String]): Unit = {
        val out = System.out;
        val scoreCounter = runGetScoreCounter()

        out.printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        out.printf("Your base grade for excercise 4.2 will be: %.2f\n", scoreCounter.fraction() * Tests().MaxPoints)
        out.printf("Fractiontouseforcodegrade %.2f", scoreCounter.fraction())
    }
}
