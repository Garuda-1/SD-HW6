package tokenizer;

public abstract class TokenizerState {

    Tokenizer tokenizer;

    TokenizerState(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void nextState();
}
