package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;

import java.io.OutputStream;
import java.io.PrintWriter;

public class PrintVisitor implements TokenVisitor {

    private final PrintWriter printWriter;

    public PrintVisitor(OutputStream outputStream) {
        this.printWriter = new PrintWriter(outputStream);
    }

    @Override
    public void visit(NumberToken numberToken) {
        printWriter.print(numberToken.getNumber());
        printWriter.print(' ');
    }

    @Override
    public void visit(BraceToken braceToken) {
        throw new IllegalStateException("Braces are not expected in Polish notation");
    }

    @Override
    public void visit(OperationToken operationToken) {
        switch (operationToken.getOperationType()) {
            case ADD: {
                printWriter.print('+');
                break;
            }
            case SUBTRACT: {
                printWriter.print('-');
                break;
            }
            case MULTIPLY: {
                printWriter.print('*');
                break;
            }
            case DIVIDE: {
                printWriter.print('/');
                break;
            }
        }
        printWriter.print(' ');
    }

    public void flush() {
        printWriter.println();
        printWriter.flush();
    }
}
