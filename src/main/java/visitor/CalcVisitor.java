package visitor;

import exception.CalcException;
import token.BraceToken;
import token.NumberToken;
import token.OperationToken;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class CalcVisitor implements TokenVisitor {

    private final Deque<BigInteger> stack = new ArrayDeque<>();

    @Override
    public void visit(NumberToken numberToken) {
        stack.addLast(numberToken.getNumber());
    }

    @Override
    public void visit(BraceToken braceToken) {
        throw new IllegalStateException("Braces are not expected in Polish notation");
    }

    @Override
    public void visit(OperationToken operationToken) throws CalcException {
        if (stack.size() < 2) {
            throw new CalcException("Given tokens are not in Polish notation");
        }
        BigInteger a = stack.removeLast();
        BigInteger b = stack.removeLast();

        try {
            switch (operationToken.getOperationType()) {
                case ADD: {
                    stack.addLast(a.add(b));
                    break;
                }
                case SUBTRACT: {
                    stack.addLast(b.subtract(a));
                    break;
                }
                case MULTIPLY: {
                    stack.addLast(a.multiply(b));
                    break;
                }
                case DIVIDE: {
                    stack.addLast(b.divide(a));
                    break;
                }
            }
        } catch (ArithmeticException e) {
            throw new CalcException("Arithmetic exception occurred: " + e.getMessage());
        }
    }

    public BigInteger getResult() {
        if (stack.size() != 1) {
            throw new CalcException("Given tokens are not in Polish notation");
        }
        return stack.getLast();
    }
}
