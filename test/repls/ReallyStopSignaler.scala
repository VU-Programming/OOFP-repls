package repls

import org.scalatest.concurrent.Signaler

object ReallyStopSignaler extends Signaler {
    override def apply(testThread: Thread): Unit = {
        StopRunningNow.stopRunningNowUnsafe(testThread)
    }
}
