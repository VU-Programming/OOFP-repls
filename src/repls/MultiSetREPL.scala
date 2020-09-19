package repls

class MultiSetREPL extends REPLBase {
    // Have a REPL of a MutliSet of characters
    override type Base = MultiSet[Char]
    override val replName: String = "" // TODO: name me!

    override def readEval(command: String): String = {
        // TODO: complete me!
        ""
    }

    // TODO: Implement any further functions that are specifically for an MultiSetREPL
}
