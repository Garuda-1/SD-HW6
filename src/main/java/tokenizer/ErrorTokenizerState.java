package tokenizer;

public class ErrorTokenizerState extends TokenizerState {

    ErrorTokenizerState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        super.tokenizer.setFinished();
    }
}
