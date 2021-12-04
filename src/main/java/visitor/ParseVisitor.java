package visitor;

import exception.ParserException;
import token.BraceToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParseVisitor implements TokenVisitor {

    private final Deque<Token> stack = new ArrayDeque<>();
    private final List<Token> polishNotationOrder = new ArrayList<>();

    @Override
    public void visit(NumberToken numberToken) {
        polishNotationOrder.add(numberToken);
    }

    @Override
    public void visit(BraceToken braceToken) throws ParserException {
        if (braceToken.getBraceType() == BraceToken.BraceType.LEFT) {
            stack.addLast(braceToken);
        } else {
            while (!stack.isEmpty()) {
                Token topToken = stack.removeLast();
                if (topToken instanceof BraceToken && ((BraceToken) topToken).getBraceType() == BraceToken.BraceType.LEFT) {
                    return;
                } else {
                    polishNotationOrder.add(topToken);
                }
            }
            throw new ParserException("Not matching braces detected (missing left)");
        }
    }

    @Override
    public void visit(OperationToken operationToken) throws ParserException {
        int priority = operationToken.getOperationType().getPriority();
        while (!stack.isEmpty()) {
            Token topToken = stack.getLast();
            if (!(topToken instanceof OperationToken)) {
                break;
            }
            if (((OperationToken) topToken).getOperationType().getPriority() < priority) {
                polishNotationOrder.add(topToken);
                stack.removeLast();
            } else {
                break;
            }
        }
        stack.addLast(operationToken);
    }

    public List<Token> getPolishNotationOrder() throws ParserException {
        while (!stack.isEmpty()) {
            Token topToken = stack.removeLast();
            if (topToken instanceof OperationToken) {
                polishNotationOrder.add(topToken);
            } else {
                throw new ParserException("Not matching braces detected (missing right)");
            }
        }
        return new ArrayList<>(polishNotationOrder);
    }
}
