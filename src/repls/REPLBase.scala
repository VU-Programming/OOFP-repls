package repls

abstract class REPLBase extends REPL {
    type Base

    // splits string into parts a+bcd-x{a, b , c}  -> Seq("a","+","bcd","-","x","{a, b , c}")
    def splitExpressionString(exp : String) : Seq[String] = {
        val builder = Seq.newBuilder[String]
        var curString = ""

        var inLiteral = false
        def addNonemptyCurAndReset() : Unit =
            if(curString.nonEmpty) {
                builder.addOne(curString)
                curString = ""
            }

        for(c <- exp) {
            if(inLiteral) {
                if(c == '}') {
                    curString+= c
                    addNonemptyCurAndReset()
                    inLiteral = false
                }
            }
            c match {
                case '{'                    => inLiteral = true
                case ' ' | '\t'             =>   addNonemptyCurAndReset()
                case ',' | '+' | '*' | '-'  => { addNonemptyCurAndReset(); builder.addOne(c.toString)}
                case _ if c.isLetterOrDigit => curString += c
                case _                      => throw new Exception("Do not know how to parse " + c )
            }
        }
        builder.result()
    }

    // TODO: put code shared by IntREPL and MultiSetREPL here
}
