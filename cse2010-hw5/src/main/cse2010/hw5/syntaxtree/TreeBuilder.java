package cse2010.hw5.syntaxtree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeBuilder {
    private static Map<String, Integer> operators = new HashMap<>();
    static {
        // 교수님 제공 뼈대 설정 그대로 유지
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

    public static SyntaxTree buildFromPostfix(String expression) {
        initStacks();

        String[] tokens = Utils.parse(expression);
        for (String token : tokens) {
            if (!operators.containsKey(token)) {
                SyntaxTree t = new SyntaxTree();
                t.addRoot(token);
                operandStack.push(t);
            } else {
                SyntaxTree right = operandStack.pop();
                SyntaxTree left = operandStack.pop();

                SyntaxTree t = new SyntaxTree();
                t.addRoot(token);
                t.attach(t.root(), left, right);

                operandStack.push(t);
            }
        }
        return operandStack.isEmpty() ? null : operandStack.pop();
    }

    public static SyntaxTree buildFromInfix(String expression) {
        initStacks();

        String[] tokens = Utils.parse(expression);
        for (String token : tokens) {
            if (!operators.containsKey(token)) {
                // 1. 피연산자 처리
                SyntaxTree t = new SyntaxTree();
                t.addRoot(token);
                operandStack.push(t);
            } else if (token.equals("(")) {
                // [교수님 힌트 반영] 스택에 보관할 때는 스택 내 우선순위인 "{"로 치환하여 저장
                operatorStack.push("{");
            } else if (token.equals(")")) {
                // 3. 닫는 괄호는 대칭점인 "{"를 만날 때까지 연산자들을 조립
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("{")) {
                    processOperator();
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop(); // "{" 기호 제거
                }
            } else {
                // 4. 일반 연산자 처리
                // 스택 내부용 괄호 기호 "{"의 우선순위는 0이므로, 0 >= 현재연산자(2 or 3)는 항상 거짓이 되어 자동으로 루프를 통과합니다.
                while (!operatorStack.isEmpty() && operators.get(operatorStack.peek()) >= operators.get(token)) {
                    processOperator();
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator();
        }

        return operandStack.isEmpty() ? null : operandStack.pop();
    }

    private static void processOperator() {
        if (operatorStack.isEmpty() || operandStack.size() < 2) return;
        String op = operatorStack.pop();
        SyntaxTree right = operandStack.pop();
        SyntaxTree left = operandStack.pop();

        SyntaxTree t = new SyntaxTree();
        t.addRoot(op);
        t.attach(t.root(), left, right);

        operandStack.push(t);
    }

    private static void initStacks() {
        operandStack.clear();
        operatorStack.clear();
    }
}
