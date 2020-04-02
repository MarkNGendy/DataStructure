package eg.edu.alexu.csd.datastructure.stacks.cs18011305;

import java.util.Scanner;

public class ExpressionEvaluator extends Stack implements IExpressionEvaluator {
    public static int operatorsPrecedence(char operator) {
        switch (operator) {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
        }
        return -1;
    }

    @Override
    public String infixToPostfix(String expression) {
        if (expression.length() == 0) {
            throw new RuntimeException("Invalid input");
        } else if (expression == null) {
            throw new RuntimeException("Invalid input");
        }
        StringBuilder postfixExpr = new StringBuilder();
        Stack opStack = new Stack();
        for (int i = 0; i < expression.length(); i++) {
            char temp = expression.charAt(i);
            if (temp == ' ') {
                continue;
            }
            if (operatorsPrecedence(temp) > 0) {
                if (temp == '-' && !isDigit(expression.charAt(i - 2))) {
                        postfixExpr.append(" 0 ");
                        int j = i+1;
                        while (Character.isDigit(expression.charAt(j))) {
                            postfixExpr.append(expression.charAt(j));
                            if (j < expression.length()-1) {
                                j++;
                            }
                            else {
                                break;
                            }
                        }
                        postfixExpr.append(" -");
                        i = j;
                        continue;
                }
                while (!opStack.isEmpty() && operatorsPrecedence(temp) <= operatorsPrecedence((Character) opStack.peek())) {
                    postfixExpr.append(" ").append(opStack.pop());
                }
                opStack.push(temp);
            } else if (temp == '('){
                opStack.push(temp);
                if (i != 0) {
                    postfixExpr.append(" ");
                }
            } else if (temp == ')'){
                char lastOperator = (char) opStack.pop();
                while (lastOperator != '(') {
                    postfixExpr.append(" ").append(lastOperator);
                    lastOperator = (char) opStack.pop();
                }
            } else {
                if (i == 0) {
                    postfixExpr.append(temp);
                } else if (Character.isDigit(expression.charAt(i-1))) {
                    postfixExpr.append(temp);
                } else if (expression.charAt(i-1) == '(') {
                    postfixExpr.append(temp);
                } else {
                    postfixExpr.append(" ").append(temp);
                }
            }
        }
        int stackSize = opStack.size();
        for (int j = 0; j < stackSize; j++) {
            postfixExpr.append(" ").append(opStack.pop());
        }
        return postfixExpr.toString();
    }


    public float evaluateOperation (float secondOp, float firstOp, char op) {
        switch (op) {
            case '+': return secondOp + firstOp;
            case '-': return firstOp - secondOp;
            case '*': return firstOp * secondOp;
            case '/': if (secondOp == 0) {
                throw new RuntimeException("cannot divide by zero");
            } return firstOp / secondOp;
        }
        return 0;
    }
    @Override
    public int evaluate(String expression) {
        String[] expr = expression.split(" ");
        Stack stack = new Stack();
        for (int i = 0; i < expr.length; i++) {
            if (expr[i].length() == 1) {
                char operator = expr[i].charAt(0);
                if (operatorsPrecedence(operator) > 0) {
                    float secondOp = (float) stack.pop();
                    float firstOp = (float) stack.pop();
                    float operationRes = evaluateOperation(secondOp, firstOp, operator);
                    stack.push(operationRes);
                } else {
                    stack.push(Float.parseFloat(String.valueOf(operator)));
                }
            } else {
                stack.push(Float.parseFloat(String.valueOf(expr[i])));
            }
        }
        float res = (float) stack.pop();
        return Math.round(res);
    }

        public boolean isDigit ( char c){
            if (Character.isDigit(c) || c == ')' || Character.isLetter(c)) {
                return true;
            }
            return false;
        }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        System.out.println("Please enter the expression:");
        String expr = in.nextLine();
        System.out.println("Choose the number of the operation you want");
        System.out.println("1- convert the expression to postfix");
        System.out.println("2- evaluate a postfix expression");
        System.out.println("enter 0 to exit");
        String input = in.nextLine();
        int choice = Integer.parseInt(String.valueOf(input.charAt(0)));
        while (choice != 0) {
            if (choice == 1) {
                String postFix = evaluator.infixToPostfix(expr);
                System.out.println("postfix expression is: " + postFix);
            } else if (choice == 2) {
                if (isValid(expr)) {
                    int postFix = evaluator.evaluate(expr);
                    System.out.println("The result is: " + postFix);
                }
            } else {
                System.out.println("Invalid input");
                System.out.println("please enter your choice");
            }
            System.out.println("Please enter the expression:");
            expr = in.nextLine();
            System.out.println("Choose the number of the operation you want");
            System.out.println("1- convert the expression to postfix");
            System.out.println("2- evaluate a postfix expression");
            System.out.println("enter 0 to exit");
            input = in.nextLine();
            choice = Integer.parseInt(String.valueOf(input.charAt(0)));
        }
    }
    public static boolean isValid(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!Character.isDigit(c) && operatorsPrecedence(c) < 0 && c != ' ') {
                return false;
            }
        }
        return true;
    }
}
