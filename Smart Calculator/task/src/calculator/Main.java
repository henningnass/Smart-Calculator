package calculator;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        SmartCalculator sc = new SmartCalculator();

        while (!sc.isExitCommand(input)){
            sc.printAnswer(input);
            input = scanner.nextLine();

        }

        System.out.println("Bye!");

    }

}

class SmartCalculator {
    private Map<String, BigInteger> actualVariables = new HashMap<>();
    String nextMessage = "";

    public void changeMessage(String message) {
        this.nextMessage = message;
    }

    public void printNextMessage() {
        if (nextMessage.length() > 0) {
            System.out.println(nextMessage);
        }
    }

    private void addValue(String name, BigInteger value) {
        actualVariables.put(name, value);
    }

    private Set<String> getIdentifiers() {
        return actualVariables.keySet();
    }

    private BigInteger getValue(String name) {
        return actualVariables.get(name);
    }


    private BigInteger calculateExpression(Deque<String> token) {

        // replace all variables by its value

        BigInteger value = null;
        Deque<String> infix = new ArrayDeque<>();
        int nrToken = token.size();
        for (int i = 0; i < nrToken; i++) {
            String nextToken = token.pollFirst();
            if (isNumber(nextToken) || isLeftParanthesis(nextToken) ||
                    isRightParanthesis(nextToken) || isOperation(nextToken)) {
                infix.offerLast(nextToken);
            } else if (isInvalidIdentifier(nextToken)) {
                changeMessage("Invalid identifier");
                return null;
            } else if (isUnknownIdentifier(nextToken)) {
                changeMessage("Unknown identifier");
                return null;
            } else {
                infix.offerLast(actualVariables.get(nextToken).toString());
            }

        }



        Deque<String> postfix = fromInfixToPostFix(infix);
        if (postfix == null) {
            changeMessage("Invalid expression");
            return null;
        } else {
            value = fromPostFixToValue(postfix);
            if (value != null) {
                changeMessage(value.toString());
                return value;
            } else {
                changeMessage("Invalid expression");
                return null;
            }
        }
    }

    public boolean isUnknownIdentifier(String name) {
        return !actualVariables.containsKey(name);
    }


    public String reduceMinus(String input) {
        StringBuilder sb = new StringBuilder(input);
        int n = sb.indexOf("--");
        while (n > 0) {
            int nrMinus = 1;
            int index = n+1;
            while (index < sb.length() && sb.charAt(index) == '-') {
                nrMinus++;
                index++;
            }
            sb.delete(n + 1, index);
            if (nrMinus % 2 == 0) {
                sb.setCharAt(n, '+');
            } else {
                sb.setCharAt(n, '-');
            }
            n = sb.indexOf("--");
        }
        return sb.toString();
    }

    /**
     * get infix Queue
     * @param input is mathematical expression as a string in infix notation
     * @return null, if expression is wrong, other queue of tokens
     */
    public Deque<String> getToken(String input) {
        Deque<String> queue = new ArrayDeque<>();

        input = input.replaceAll(" ", "");
        input = input.replaceAll("\\+{2,}", "+");
        input = reduceMinus(input);



        // possibly wrong expression? too many * signs?


        String regex = "(\\+-|\\+\\*|\\+/|-\\+|-\\*|-/|\\*\\+|\\*-|\\*\\*|\\*/|/\\+|/-|/\\*|//)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            changeMessage("Invalid expression");
            return null;
        }


        StringTokenizer token = new StringTokenizer(input, "\\+-\\*/()=", true);
        while (token.hasMoreTokens()) {
            String nextToken = token.nextToken();
            queue.offerLast(nextToken);
        }
        return queue;
    }
    


    public static boolean isCommand(String input) {
        String regex = "\\s*/\\w+\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public  boolean isNumber(String input) {
        String regexNumber = "(\\s)*(\\+|-){0,1}([1-9]{1}[0-9]*|0)\\s*";
        Pattern pattern = Pattern.compile(regexNumber);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private  void answerCommand(String input) {
        if ("/help".equals(input)) {
            changeMessage("The program calculates the sum of numbers");
        } else {
            changeMessage("Unknown command");
        }
    }

    private static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }

        return result;
    }

    public static boolean isExitCommand(String input) {
        return  (isCommand(input) && "/exit".equals(input));
    }

    public static boolean isEquation(Deque<String> token) {
        return token.contains("=");
    }

    private void readVariableFromEquation(Deque<String> token) {
        String variable = token.pollFirst();
        changeMessage("");
        if (isInvalidIdentifier(variable)) {
            changeMessage("Invalid identifier");
        } else {
            // delete = sign
            token.pollFirst();
            String nextToken = token.peekFirst();
            if ("+".equals(nextToken) || "-".equals(nextToken)) {
                token.offerFirst("0");
            }
            BigInteger result = calculateExpression(token);
            if (result != null) {
                actualVariables.put(variable, result);
                changeMessage("");
            }
        }

    }

    public boolean isInvalidIdentifier(String name) {
        return !name.matches("[a-zA-Z]+");
    }

    public  void printAnswer(String input) {
        if (isCommand(input)) {
            answerCommand(input);
        } else if ("".equals(input)) {
            changeMessage("");
        } else {
            Deque<String> token = getToken(input);
            if (token != null) {
                evaluateExpression(token);
            }
        }
        printNextMessage();
    }

    private void evaluateExpression(Deque<String> token) {
        if (isEquation(token)) {
            // try to read variable from token
            readVariableFromEquation(token);
        } else {
            // make calculations
            calculateExpression(token);
        }
    }

    public int getPrecedence(String c) {
        int pre;
        switch (c) {
            case "+": pre = 1; break;
            case "-": pre = 1; break;
            case "*": pre = 2; break;
            case "/": pre = 2; break;
            default : pre = 0; break;
        }
        return pre;
    }

    private boolean isOperation(String c) {
        return "+".equals(c) || "-".equals(c) || "*".equals(c) || "/".equals(c);
    }

    private boolean isLeftParanthesis(String c) {
        return "(".equals(c);
    }

    private boolean isRightParanthesis(String c) {
        return ")".equals(c);
    }

    public Deque<String> fromInfixToPostFix(Deque<String> infix) {
        Deque<String> postfix = new ArrayDeque<>();
        Deque<String> operators = new ArrayDeque<>();

        int numberOfEntries = infix.size();

        try {
            for (int i = 0; i < numberOfEntries; i++) {
                String entry = infix.pollFirst();
                if (isNumber(entry)) {
                    postfix.offerLast(entry);
                } else if (isLeftParanthesis(entry)) {
                    operators.offerLast(entry);
                } else if (isRightParanthesis(entry)) {
                    while (!"(".equals(operators.peekLast())) {
                        // STEP 5 of Algorithm.
                        String op = operators.pollLast();

                        String first = postfix.pollLast();          // get the two operands.
                        String second = postfix.pollLast();

                        postfix.offerLast(second);
                        postfix.offerLast(first);
                        postfix.offerLast(op);

                    }

                    operators.pollLast();     // pop '(' from stack.
                } else if (isOperation(entry)) {
                    // check precedence of each operator with top of the stack and process them.
                    while (operators.size() > 0 && !")".equals(operators.peekLast())
                            && this.getPrecedence(entry) <= this.getPrecedence(operators.peekLast())) {
                        String op = operators.pollLast();

                        String first = postfix.pollLast();
                        String second = postfix.pollLast();

                        postfix.offerLast(second);
                        postfix.offerLast(first);
                        postfix.offerLast(op);

                    }

                    operators.offerLast(entry);
                }
            }
            while (operators.size() > 0) {
                String op = operators.pollLast();

                String first = postfix.pollLast();
                String second = postfix.pollLast();

                postfix.offerLast(second);
                postfix.offerLast(first);
                postfix.offerLast(op);
            }

        } catch (Exception e) {
            return null;
        }


        return postfix;
    }


    public BigInteger fromPostFixToValue(Deque<String> postfix) {
        Deque<String> queue = new ArrayDeque<>();
        String actualEntry;
        String entry1;
        String entry2;
        String resultEntry = null;
        int nrIterations = postfix.size();


        try {
            for (int i = 0; i < nrIterations; i++) {
                actualEntry = postfix.poll();
                if (isNumber(actualEntry)) {
                    queue.offerLast(actualEntry);
                } else {
                    char operation = actualEntry.charAt(0);
                    entry1 = queue.pollLast();
                    entry2 = queue.pollLast();
                    BigInteger num1 = new BigInteger(entry1);
                    BigInteger num2 = new BigInteger(entry2);
                    BigInteger result = operation == '+' ? num2.add(num1) :
                                        operation == '-' ? num2.subtract(num1) :
                                        operation == '*' ? num2.multiply(num1) : num2.divide(num1);
                    resultEntry = result.toString();

                    queue.offerLast(resultEntry);
                }
            }
            return new BigInteger(queue.pollLast());
        } catch (Exception e) {
            return null;
        }

    }

}

