package token;

import visitor.TokenVisitor;

public class BraceToken implements Token {

    private final BraceType braceType;

    public BraceToken(BraceType braceType) {
        this.braceType = braceType;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public BraceType getBraceType() {
        return braceType;
    }

    public enum BraceType {
        LEFT,
        RIGHT
    }
}
