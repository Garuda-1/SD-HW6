import exception.TokenizerException;
import token.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) throws TokenizerException {
        Tokenizer tokenizer = new Tokenizer(System.in);
        List<Token> infixNotationTokens = tokenizer.getTokens();

        ParseVisitor parseVisitor = new ParseVisitor();
        for (Token token : infixNotationTokens) {
            token.accept(parseVisitor);
        }
        List<Token> polishNotationTokens = parseVisitor.getPolishNotationOrder();

        System.out.println("Forming Polish notation...");
        PrintVisitor printVisitor = new PrintVisitor(System.out);
        for (Token token : polishNotationTokens) {
            token.accept(printVisitor);
        }
        printVisitor.flush();

        System.out.println("Calculating...");
        CalcVisitor calcVisitor = new CalcVisitor();
        for (Token token : polishNotationTokens) {
            token.accept(calcVisitor);
        }
        System.out.println(calcVisitor.getResult());
    }
}
