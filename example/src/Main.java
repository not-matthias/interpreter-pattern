import expressions.*;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //
        // Create expression tree from string
        //
        var tokenString = "4 3 2 - 1 + *";
        var result = createExpressionTree(tokenString);
        System.out.println(String.format("%s = %s", result, result.interpret()));

        //
        // Create expression tree manually
        //
        var customExpression = new PlusExpression(
                new MultiplyExpression(new NumberExpression(12), new NumberExpression(2)),
                new DivideExpression(new NumberExpression(64), new NumberExpression(2))
        );
        System.out.println(String.format("%s = %s", customExpression, customExpression.interpret()));
    }

    private static AbstractExpression createExpressionTree (String tokenString) {
        var tokenList = tokenString.split(" ");
        var stack = new Stack<AbstractExpression>();

        // Iterate though the token list.
        for (var token : tokenList) {
            if (isOperator(token)) {
                var rhs = stack.pop();
                var lhs = stack.pop();

                var operator = getOperator(token, lhs, rhs);

                stack.push(operator);
            } else {
                var number = new NumberExpression(token);
                stack.push(number);
            }
        }

        return stack.pop();
    }

    private static AbstractExpression getOperator(String operator, AbstractExpression lhs, AbstractExpression rhs) {
        switch (operator) {
            case "+":
                return new PlusExpression(lhs, rhs);

            case "-":
                return new MinusExpression(lhs, rhs);

            case "*":
                return new MultiplyExpression(lhs, rhs);

            case "/":
                return new DivideExpression(lhs, rhs);

            default:
                throw new IllegalArgumentException("Invalid operator specified.");
        }
    }

    private static boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/");
    }
}
