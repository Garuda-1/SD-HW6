package tokenizer;

import exception.TokenizerException;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final Reader reader;
    private final List<Token> tokens = new ArrayList<>();
    private boolean finished = false;

    private TokenizerState tokenizerState = new StartTokenizerState(this);
    private int nextCharacter = Integer.MIN_VALUE;

    public Tokenizer(InputStream inputStream) {
        this.reader = new InputStreamReader(inputStream);
    }

    public List<Token> getTokens() throws TokenizerException {
        while (!finished) {
            tokenizerState.nextState();
        }
        if (tokenizerState instanceof ErrorTokenizerState) {
            throw new TokenizerException();
        }
        return tokens;
    }

    void next() throws IOException {
        nextCharacter = reader.read();
    }

    int getNextCharacter() {
        return nextCharacter;
    }

    void createToken(Token token) {
        tokens.add(token);
    }

    void setTokenizerState(TokenizerState tokenizerState) {
        this.tokenizerState = tokenizerState;
    }

    void setFinished() {
        this.finished = true;
    }
}
