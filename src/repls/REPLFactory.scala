package repls

/*
In this exercise you will be making a two calculator REPLs (read eval print loops). The first of these is a regular calculator dealing with integers. The REPL has three types of commands:

* Evaluate expression : If the input is an expression (for example: 1 + n * 3 + 5) then the outcome should be its result (for example: 18 from the earlier example, assuming that n = 4 )
* Assign variable: If the input is an assignment (for example: n = 18 * m * 2 + 5), the REPL should store the binding of that variable and print the new binding (in this case: n = 77).
* Simplify expresion: If the input starts with an "@" then the repl should simplify the expression after the "@" according to the rules below. Example "@ (n * 0) + (n * 2) + (n * 3) + a * b " should give "n * (2 + 3) + a * b"

The second REPL is similar, but instead of dealing with integers, it deals with a different base type: Multisets of names. A multiset is like a set, but allows for duplicates. The number of instances of an element in a multiset is called its multiplicity. For example in the multiset { a, a, a, b, a}, a has a multiplicity of 4, and b has a multiplicty of 1. The multiplicity is always an integer and never negative. The multiplicity of an element which is not in the multiset is 0.

You will implement three multiset operations:

Sum : a + b, is the multiset c such that:

m_c(x) = m_a(x) + m_b(x) forall x

where m_c(x) is the multiplicitiy of in the set c.

Intersection : a * b, is the multiset c such that:

m_c(x) = min(m_a(x),m_b(x)) forall x

Subtraction: a - b, is the multiset c such that:

m_c(x) = max(m_a(x) - m_b(x),0) forall x

Examples:

{ a, a, b, c} + {a,b,b,c,c} * {a,c,d}
> {a,a,a,b,b,c,c}

A lot of the techniques discussed in this course are useful to enable code reuse. This assignment gives you ample opportunity to employ these tecnniques to, as much of the functionality of the two REPLs are the same.

Rules to rewrite simplification (for both REPLS):

distributivity:

(a * b) + (a * c) -> a * (b + c)

(and all 3 other forms of this, where the left hand side is : (b * a) + (a * c), (a * b) + (b * a), (b * a) + (c * a)

Rules for Integer calculator REPL:

0 + e -> e
e + 0 -> e
1 * e -> e
e * 1 -> e
e * 0 -> 0
0 * e -> 0

Rules for Multiset calculator REPL:

e * e -> e
{} * e -> {}
e * {} -> {}
e + {} -> e
{} + e -> e

This assignment requires you to parse expressions such as a + b * c and deal with operator precendence (i.e. this expression is parsed as a + (b * c)". One way of doing this is by converting the expression into reverse polish by using the Shunting Yard algorith (http://mathcenter.oxford.emory.edu/site/cs171/shuntingYardAlgorithm/)

We also expect you to "pretty print" a simplified expression such as ((a * b) + (c * d)) as a * b + c * d. For this, it is handy to realize when parenthesis are needed when printing. If the current expression is l `op` r, where l is the left-sub expression, r is the right sub-expression and `op` is the operator, then parenthesis are needed around l iff:
* l is an expression with an operator `op2` AND
* `op2` has lower precedence than `op`

Operator used and their precedence
Op  precedence level
+        2
-        2
*        3


Suggested approach:

1. Completely implement the Integer calculator Repl and make sure it passes all the tests. .
2. Implement a generic, immutable multiset.
3. Implement the Multiset calculator Repl by copying and modifying code from the Integer Repl and make sure it passes all the tests. Your code now works, but has a lot of duplicated code, which hinders maintenance and readability.
4. Refactor your code such that the common parts of both REPLs are shared.

 */


object REPLFactory {

    def makeIntREPL() : REPL = new IntREPL()
    def makeMultiSetREPL() : REPL = new MultiSetREPL()

}
