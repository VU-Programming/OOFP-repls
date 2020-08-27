# Advanced Programming: REPL Calculators

In this exercise you will be making two calculators [REPLs](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop). The first of these is a regular calculator dealing with integers, the second operates on [MultiSets](#multisets). A lot of the techniques discussed in this course are useful to enable code reuse and this assignment gives you ample opportunity to employ these techniques since both REPLs have very similar functionality.
 
The REPLs have three types of command:

- **Expression Evaluation:** If the input is an expression (for example: `1 + 4 * 3 + 5`) then the output should be its result (18 in this example)
- **Variable Assignment:** If the input is an assignment (for example: `n = 18 * m * 2 + 5`, the REPL should store the binding of that variable and print the new binding (in this case: `n = 77`, assuming m = 2).
- **Expression Simplification:** If the input starts with an "@" then the REPL should simplify the expression after the "@" according to the [rules below](#simplification-rules). For example, `@ ( ( n * 2 ) + ( n * 3 ) ) + a * b` should give `n * 5 + a * b`. Notice that we compute the `n * (2 + 3) = n * 5`, you should do the same. Notice that you do not have to simplify `5 * n * m * 3` to `15 * n * m`, but its bonus points if you do. 

Note that evaluation and simplification only differ when dealing with unbound variables. Evaluating unbound variables give an error, but they can be simplified. Bound variables should be treated as their corresponding value both when simplifying and evaluating. For example:
```
n = 18 + 2
> n = 20
n * n 
> 400
@ ( n * 2) + m * 1
> 40 + m
( n * 2 ) + m * 1
Unkown variable: m
```

## MultiSets
A multiset is like a set, but allows for duplicates. The number of instances of an element in a multiset is called its *multiplicity*. For example, in the multiset `{ a, a, a, b, a }`, `a` has a multiplicity of 4, and `b` has a multiplicity of 1. The multiplicity is always an integer and never negative. The multiplicity of an element which is not in the multiset is 0.

You will implement three multiset operations:

- **Summation:** a + b is the multiset c such that:
    
    <code>∀x m<sub>c</sub>(x) = m<sub>a</sub>(x) + m<sub>b</sub>(x)</code>

    where <code>m<sub>c</sub>(x)</code> is the multiplicity of x in the set c.

- **Intersection:** a * b is the multiset c such that:

    <code>∀x m<sub>c</sub>(x) = min(m<sub>a</sub>(x), m<sub>b</sub>(x))</code>

- **Subtraction:** a - b is the multiset c such that:

    <code>∀x m<sub>c</sub>(x) = max(m<sub>a</sub>(x) - m<sub>b</sub>(x), 0)</code>

**Example:**

`{a,a,b,c} + {a,b,b,c,c} * {a,c,d} = {a,a,a,b,c,c}`

## Simplification Rules:

**Distributivity:**

- `( a * b ) + ( a * c ) → a * ( b + c )`
- `( b * a ) + ( a * c ) → a * ( b + c )`
- `( a * b ) + ( b * a ) → a * ( b + c )`
- `( b * a ) + ( c * a ) → a * ( b + c )`

**Rules for Integer calculator REPL:**

- `0 + e → e`
- `e + 0 → e`
- `1 * e → e`
- `e * 1 → e`
- `e * 0 → 0`
- `0 * e → 0`

**Rules for Multiset calculator REPL:**

- `e * e → e`
- `{} * e → {}`
- `e * {} → {}`
- `e + {} → e`
- `{} + e → e`

## Suggested approach:
1. Completely implement the Integer calculator Repl and make sure it passes all the tests. .
2. Implement the generic, immutable multiset. 
3. Implement the Multiset calculator Repl by copying and modifying code from the Integer Repl and making sure it passes all the tests.
4. Your code now works, but has a lot of duplicated code, which hinders maintenance and readability. Refactor your code such that the common parts of both REPLs are shared.

More detailed approach for constructing a REPL:
1. Convert Infix expression to RPN using the Shunting yard algorithm (below).
2. Convert RPN to a parse tree (an example of this is [here](https://gitlab.com/vu-oofp/lecture-code/-/blob/master/OOReversePolish.scala), which is discussed in the last 3 videos of these [video lectures](https://www.youtube.com/playlist?list=PLi-VVX8q87FIzFCmzXCc_JZZJkvW80C66))
3. Simplify the parse tree using pattern matching ([lecture sides on pattern matching](#https://docs.google.com/presentation/d/1GPbegITJlA3EOkbhU9SLepxQgg6z1IARi0l6RiZrKsc/edit?usp=sharing)) and dynamic dispatch ([videos on dynamic dispatch]([video lectures](https://www.youtube.com/playlist?list=PLi-VVX8q87FIzFCmzXCc_JZZJkvW80C66)).

### Tokenization
For parsing the input strings, you need to know the meaning of the characters. [Tokenization](https://en.wikipedia.org/wiki/Lexical_analysis#Tokenization) is the processes of giving abstract parts meaning. For example, giving `+` the token of an operator. This can be used in combination with [pattern matching](#pattern-matching) for the [Shunting Yard algorithm](#shunting-yard-algorithm). You could match on the constants, variables, and operators, in an expression.

### Shunting Yard Algorithm
This assignment requires you to parse expressions such as `a + b * c` and deal with operator precedence (i.e. this expression parses to `a + ( b * c )`). One way of doing this is by converting the expression into reverse polish notation by using the [Shunting Yard algorithm](http://mathcenter.oxford.emory.edu/site/cs171/shuntingYardAlgorithm/).

### Pretty Printing
We also expect you to "pretty print" a simplified expression such as `( ( a * b ) + ( c * d ) ) as a * b + c * d`. For this, it is handy to realize when parentheses are required: if the current expression is `l op r`, where `l` is the left-sub expression, `r` is the right sub-expression and `op` is the operator, then parentheses are needed around `l` iff:
* `l` is an expression with an operator `op2` AND
* `op2` has lower precedence than `op`

### Operators used and their precedence

| Operator | precedence level |
|:---:     |:----------------:|
| +        | 2                |
| -        | 2                |
| *        | 3                |


### Techniques for code reuse
##### Inheritance
Scala, and other high level languages, offers many techniques to have good code reuse. With inheritance, you can reuse code for classes which the same structure. This allows classes to dispatch method calls dynamically, also known as [dynamic dispatching](https://en.wikipedia.org/wiki/Dynamic_dispatch). Scala will call the lowest implementation of a method. In this method you can call the method of the super class, by `super.methodName()`.

#### Pattern matching
[Pattern matching](https://docs.scala-lang.org/tour/pattern-matching.html) ([lecture sides on pattern matching](#https://docs.google.com/presentation/d/1GPbegITJlA3EOkbhU9SLepxQgg6z1IARi0l6RiZrKsc/edit?usp=sharing)) is another powerful feature of Scala, especially on case classes. This makes matching cases extremely easy and with few lines of code. This improves maintenance and readability drastically.

## Format of the assignment and tests
You may have noticed the examples we have given in these instructions, have spaces before and after the brackets. For example in `( 2 + 3 ) * 2`. This is intentional, making parsing easier and more reliable. For this reason, pretty printing should return the same, this making it possible to use the output of the pretty printing again.

### Erros
Your implementation should throw errors for at least the following:
* Invalid operator
* Unknown variable
* Invalid variable name (cannot be a number)
* Type mismatching (for example inputting a multiset in an Integer Repl expression)
* Invalid command
* No value given when assigning variable
* No expression given to simplify

## How to run your implementation

From the command line run: 
``` ./gradlew runIntREPL 
// or 
./ gradlew runMultiSetREPL```

In IntelliJ: In the gradle tab (on the right side) there is a folder/group 'repls/Tasks/runnables'. In here there are two tasks, namely: runIntREPL and runMultiSetREPL. You can run these to get a working instance of IntREPL and MultiSetREPL, respectively.

Another method would be by running the RunREPL object file, and give the respective type as argument, be it IntREPL or MultiSetREPL. This can most easily be done by going into "Edit Configurations..." (in the drop down menu next to the run button on the top). In here you can specify the type in the "Program arguments" field.

With this now you can use your REPLs as any other simple REPL, inputting an expression and outputting, hopefully, the expected output. In combination with the test examples, you can use this to help debug your implementation, and check if it returns the correct output. 
