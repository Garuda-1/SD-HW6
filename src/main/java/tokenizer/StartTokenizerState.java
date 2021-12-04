package tokenizer;

import token.BraceToken;
import token.OperationToken;

import java.io.IOException;

public class StartTokenizerState extends TokenizerState {

    StartTokenizerState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        if (super.tokenizer.getNextCharacter() == Integer.MIN_VALUE) {
            if (!readNextCharacter()) {
                return;
            }
        }
        if (super.tokenizer.getNextCharacter() == -1) {
            super.tokenizer.setTokenizerState(new ErrorTokenizerState(super.tokenizer));
            return;
        }
        char character = (char) super.tokenizer.getNextCharacter();
        if (character == '\n') {
            super.tokenizer.setTokenizerState(new EndTokenizerState(super.tokenizer));
            return;
        }
        if (Character.isDigit(character)) {
            super.tokenizer.setTokenizerState(new NumberTokenizerState(super.tokenizer));
            return;
        }
        if (!Character.isWhitespace(character)) {
            switch (character) {
                case '(': {
                    super.tokenizer.createToken(new BraceToken(BraceToken.BraceType.LEFT));
                    break;
                }
                case ')': {
                    super.tokenizer.createToken(new BraceToken(BraceToken.BraceType.RIGHT));
                    break;
                }
                case '+': {
                    super.tokenizer.createToken(new OperationToken(OperationToken.OperationType.ADD));
                    break;
                }
                case '-': {
                    super.tokenizer.createToken(new OperationToken(OperationToken.OperationType.SUBTRACT));
                    break;
                }
                case '*': {
                    super.tokenizer.createToken(new OperationToken(OperationToken.OperationType.MULTIPLY));
                    break;
                }
                case '/': {
                    super.tokenizer.createToken(new OperationToken(OperationToken.OperationType.DIVIDE));
                    break;
                }
                default: {
                    super.tokenizer.setTokenizerState(new ErrorTokenizerState(super.tokenizer));
                    return;
                }
            }
        }
        readNextCharacter();
    }

    private boolean readNextCharacter() {
        try {
            super.tokenizer.next();
            return true;
        } catch (IOException e) {
            super.tokenizer.setTokenizerState(new ErrorTokenizerState(super.tokenizer));
            return false;
        }
    }
}
