package tokenizer;

import token.NumberToken;

import java.io.IOException;
import java.math.BigInteger;

public class NumberTokenizerState extends TokenizerState {

    StringBuilder accumulator = new StringBuilder();

    NumberTokenizerState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        char character = (char) super.tokenizer.getNextCharacter();
        if (Character.isDigit(character)) {
            accumulator.append(character);
            try {
                super.tokenizer.next();
            } catch (IOException e) {
                super.tokenizer.setTokenizerState(new ErrorTokenizerState(super.tokenizer));
            }
        } else {
            BigInteger number = new BigInteger(accumulator.toString());
            super.tokenizer.createToken(new NumberToken(number));
            super.tokenizer.setTokenizerState(new StartTokenizerState(super.tokenizer));
        }
    }
}
