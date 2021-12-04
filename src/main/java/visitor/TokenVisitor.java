package visitor;

import token.BraceToken;
import token.NumberToken;
import token.OperationToken;

public interface TokenVisitor {

    void visit(NumberToken numberToken);

    void visit(BraceToken braceToken);

    void visit(OperationToken operationToken);
}
