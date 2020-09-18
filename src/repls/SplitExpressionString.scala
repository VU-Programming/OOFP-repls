package repls

object SplitExpressionString {

  /* Split strings in a way that is handy for this exercise.

  examples:

  ac+bc -> (ac,+,bc)
  ac + bc -> (ac,+,bc)
  (ac * 8) + 2 -> ((,ac,*,),+,2)


  negative number parsing:
  89-ff -> (89,-,ff)
  b - 11 -> (b, - , 11)
  if - directly borders on a character or digit on the right, but not on the left, is included in the string on the right
  b + -11 -> (b,+,-11) //

  set parsing: everything between { and } is taken as a single literal string

  {a,bc,d} + {df , sfd , a} -> ({a,bc,d}, + ,{df , sfd , a})


   */
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
      } else {
        c match {
          case '{' => {addNonemptyCurAndReset() ; inLiteral = true; curString = "{" }
          case ' ' | '\t' => addNonemptyCurAndReset()
          case '-' if curString.isEmpty => { addNonemptyCurAndReset(); curString = "-" }
          case '-' if curString.nonEmpty => { addNonemptyCurAndReset(); builder.addOne(c.toString) }
          case '(' | ')' | ',' | '+' | '*' => {
            addNonemptyCurAndReset(); builder.addOne(c.toString)
          }
          case _ if c.isLetterOrDigit => curString += c
          case _ => throw new Exception("Do not know how to parse " + c)
        }
      }
    }
    builder.result()
  }

}
