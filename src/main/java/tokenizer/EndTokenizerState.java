package tokenizer;

public class EndTokenizerState extends TokenizerState {

    EndTokenizerState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        super.tokenizer.setFinished();
    }
}
