package cse2010.hw5.syntaxtree;

import cse2010.hw5.tree.Position;
import cse2010.hw5.tree.binary.linked.LinkedBinaryTree;

import java.util.List;
import java.util.stream.Collectors;

public class SyntaxTree extends LinkedBinaryTree<String> {

    // 테스트 코드 연동용 정적 메서드 추가
    public static SyntaxTree buildFromInfix(String expression) {
        return TreeBuilder.buildFromInfix(expression);
    }

    public static SyntaxTree buildFromPostfix(String expression) {
        return TreeBuilder.buildFromPostfix(expression);
    }

    public double evaluate() {
        if (isEmpty()) return 0.0;
        return evaluate(root());
    }

    private double evaluate(Position<String> position) {
        if (isExternal(position)) {
            return Double.parseDouble(position.getElement());
        }
        double leftValue = evaluate(left(position));
        double rightValue = evaluate(right(position));
        String operator = position.getElement();

        switch (operator) {
            case "+": return leftValue + rightValue;
            case "-": return leftValue - rightValue;
            case "*": return leftValue * rightValue;
            case "/": return leftValue / rightValue;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public String toPostFix() { return cvtToString(postOrder()); }
    public String toPreFix() { return cvtToString(preOrder()); }
    public String toInfix() { return toInfix(root()); }

    private String toInfix(Position<String> position) {
        if (position == null) return "";
        if (isExternal(position)) return position.getElement();
        return "(" + toInfix(left(position)) + " " + position.getElement() + " " + toInfix(right(position)) + ")";
    }

    public String showTree() { return indentTree(root(), 0); }

    private String indentTree(Position<String> position, int level) {
        if (position == null) return "";
        Node<String> node = validate(position);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < level; i++) {
            builder.append("  ");
        }
        builder.append(node.getElement()).append("\n");

        if (left(position) != null) builder.append(indentTree(left(position), level + 1));
        if (right(position) != null) builder.append(indentTree(right(position), level + 1));

        return builder.toString();
    }

    private String cvtToString(List<Position<String>> positions) {
        return positions.stream().map(Position::getElement).collect(Collectors.joining(" "));
    }
}
