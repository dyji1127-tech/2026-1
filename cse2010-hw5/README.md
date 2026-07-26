Homework #5
===========

### Due: Jun 9 (Tuesday), 2026

### Problem: Construct arithmetic expression trees from either postfix or infix expressions.

Complete the skeleton classes both `TreeBuilder.java` and `SyntaxTree.java`.

You must use the accompanied binary tree implementation located in `cse2010.hw5.tree`.
The package contains link-based, tree-related classes discussed in lecture.
The codes are almost identical to the ones in the textbook.
The test code, `LinkedBinaryTreeTest` will help you to understand how some core methods are used.

```java
public class TreeBuilder {
    private static Map<String, Integer> operators = new HashMap<>();
    static {
        // Put relevant operators with precedences in the hash map.
        operators.put("(", 4); // opening parenthesis in expression
        operators.put("{", 0); // opening parenthesis in operator stack
        operators.put("*", 3);
        operators.put("/", 3);
        operators.put("+", 2);
        operators.put("-", 2);
        operators.put(")", 1);
    }

    private static Stack<SyntaxTree> operandStack = new Stack<>();
    private static Stack<String> operatorStack = new Stack<>();

    /**
     * Construct a syntax tree from a postfix arithmetic expression.
     */
    public static SyntaxTree buildFromPostfix(String expression) {
        initStacks();

        String[] tokens = Utils.parse(expression);
        for (String token : tokens) {
           /**
            * You code goes here ...
            */
        }

       return null;
    }
    
    /**
     * Construct a syntax free from an infix arithmetic expression.
     */
    public static SyntaxTree buildFromInfix(String expression) {
        initStacks();

        String[] tokens = Utils.parse(expression);
        for (String token : tokens) {
           /**
            * You code goes here ...
            */
        }

        return null;
    }

    private static void initStacks() {
        operandStack.clear();
        operatorStack.clear();
    }
}
```

* The `TreeBuilder` provides two construction methods: `buildFromInfix` and `buildFromPostfix`. Constructing a syntax tree from a postfix expression is relatively straight-forward. But, it maybe _(very)_ **_hard_** to construct it from an infix expression. So, you'd better start with `buildFromPostfix` first.

* The `Map` is a simple data structure that stores _(name, value)_ pairs. We can retrieve a _value_ from the `Map` using _key_. For this homework, the **key** is the **operator symbol** and the **value** is the operator's **precedence**.

    ```java
    private static Map<String, Integer> operators = new HashMap<>();
    ...
    operators.put("(", some_precedence); // some_precedence is an integer
    ...
    int precedence = operators.get("("); // null if not found
    ...
    ```

1. `buildFromPostfix` constructs an expression tree from a *postfix* expression.

    ```java
    SyntaxTree tree = SyntaxTree.buildFromPostfix("1 20 + 31 49 + *");
    ```
   will generate tree like this

    <div style="text-align: center;"><img src="tree.png" width="400"></div>

    *   Use `TreeUtil.parse()` to parse the expression and generate an array of string tokens from it.
    ```java 
    // expression = "1 20 + 31 49 + *"
    String[] tokens = parse(expression); // [1, 20, +, 31, 49, +, *]
    ``` 
    *   All operators and operands are stored as `String` in each tree node as an element.
    *   Only binary operators (`*`, `/`, `+`, `-`) need to be supported.
    *   Each operand is a single letter or an integer represented as string, possibly with multiple digits.
    *   If you need `Stack`, you may use the stack defined in **Java's collection library (java.util.Stack)**.


2. `buildFromInfix` constructs an expression tree from an *infix* expression. All the codes below

    ```java
    SyntaxTree tree = SyntaxTree.buildFromInfix("(1 + 20) * (31 + 49)");
    SyntaxTree tree = SyntaxTree.buildFromInfix("((1 + 20) * (31 + 49))");
    ```
   will generate the same tree as above figure.

    * The same guidelines given for `buildFromPostfix` also apply here. `TreeUtil.parse()` can still be used to parse the infix expression and generate an array of string tokens from it.

    ```java 
    // expression = "(x + 20) * (y + 49)"
    String[] tokens = parse(expression); // [(, x, +, 20, ), *, (, y, +, 49, )]
    ``` 
    * Operator precedence is _crucial_ when we construct syntax trees **from infix expressions**. Especially, "(" and ")" also need to be considered as operators when builing trees from infix expressions.
    * You **_must_** define operator's precedence properly.
    * You **_can_** define additional operator constants, if needed.
    * **Hint:** One or more operators' precedence(s) need to be changed whether it is (or they are) inside the *operator stack* or not.

As for the `SyntaxTree` class, you need to define one public `evaluate` method, and two private `toInfix` and `indentTree` methods.

```java
public class SyntaxTree extends LinkedBinaryTree<String> {

    /**
     * Evaluate syntax tree.
     */
    public double evaluate() {
        /**
         * Your code goes here ...
         */
        return 0d;
    }

    /**
     * Returns postfix expression corresponding to this syntax tree.
     */
    public String toPostFix() {
        return cvtToString(postOrder());
    }

    /**
     * Returns prefix expression corresponding to this syntax tree.
     */
    public String toPreFix() {
        return cvtToString(preOrder());
    }

    /**
     * Returns fully parenthesized infix expression corresponding to this syntax tree.
     */
    public String toInfix() {
        return toInfix(root());
    }

    /**
     * Returns fully parenthesized infix expression corresponding to this syntax subtree.
     */
    private String toInfix(Position<String> position) {
        /**
         * Your code goes here ...
         */
        return null;
    }

    /**
     * Returns a formatted string representation of tree hierarchy.
     * The formatted string representation of the expression tree corresponsing
     * to {@code (a + b) * (c - d)} looks as follow:
     * *
     *   +
     *     a
     *     b
     *   -
     *     c
     *     d
     */
    public String showTree() {
        return indentTree(root(), 0);
    }

    /**
     * Returns a formatted string representation of the subtree hierarchy.
     * @param level indentation level; 0 means no indentation; the unit of
     *              the indentation level is two spaces.
     */
    private String indentTree(Position<String> position, int level) {
        Node<String> node = validate(position);
        StringBuilder builder = new StringBuilder();

        /**
         * You code goes here...
         */

        return builder.toString();
    }

    /**
     * Convert list of Positions to a serialized string in which
     * each element of the position is delimited by the ' ' character.
     */
    private String cvtToString(List<Position<String>> positions) {
        return positions.stream().map(Position::getElement).collect(Collectors.joining(" "));
    }
}
```

1. `toInfix` should generate fully parenthesized *infix* expression of the tree.

    ```java
    tree.toInfix() ==> "((1 + 20) * (31 + 49))"
    ```

   **Note the spaces around each operator**.


2. `evaluate` calculates the arithmetic expression represented by the tree. Of course, if you use letters to denote operands, the expression cannot be evaluated. (You may safely ignore this case.)

    ```java
    tree.evaluate() ==> "1680.0" 
    ```

   Evaluation should be performed as **double** arithmetic.


3. `showTree` prints the tree as properly indented depending on the depth of each node.

    ```java
    tree.showTree() ==> "*\n"
                      + "  +\n"
                      + "    1\n"
                      + "    20\n"
                      + "  +\n"
                      + "    31\n"
                      + "    49\n"
    ```

   The root node has 0 indentation. Nodes at level *i* should be indented as much as `depth * 2` spaces.

### How to test?

If everything works fine, the test results may look something similar like below:

<div style="text-align: center;"><img src="test_result.png" width="400"></div>


### What to submit?

- Submit only the src folder compressed as a `.zip` file.
- Submit it through LMS under Assignments(과제 및 평가).
- Email questions to the TA (김승호, ohgnues@hanyang.ac.kr).

