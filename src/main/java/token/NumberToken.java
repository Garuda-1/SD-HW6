package token;

import visitor.TokenVisitor;

import java.math.BigInteger;

public class NumberToken implements Token {

    private final BigInteger number;

    public NumberToken(BigInteger number) {
        this.number = number;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public BigInteger getNumber() {
        return number;
    }
}
