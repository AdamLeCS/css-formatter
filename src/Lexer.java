import java.util.List;

public class Lexer {
    private List<Token> tokens;
    
    public Lexer(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void processLine(String line) {
        // read character by character and decide which tokens to create
        int startIndex = 0;
        boolean atText = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (isDelimiter(c) && !atText) {
                createToken(String.valueOf(c));
                continue;
            } else if (isDelimiter(c) && atText) {
                createToken(line.substring(startIndex, i));
                createToken(String.valueOf(c));
                atText = false;
            } else if (!isDelimiter(c) && !atText) {
                startIndex = i;
                atText = true;
            } else {
                continue;
            }
        }
    }

    private static final char[] delimiters = {' ', ',', ';', ':', '{', '}'};
    private boolean isDelimiter(char c) {
        for (char d : delimiters) {
            if (c == d) {
                return true;
            }
        }
        return false;
    }

    private void createToken(String value) {
        Token token;
        switch (value) {
            case ",":
                token = new Token(TokenType.COMMA, value);
                break;
            case ";":
                token = new Token(TokenType.SEMICOLON, value);
                break;
            case ":":
                token = new Token(TokenType.COLON, value);
                break;
            case "{":
                token = new Token(TokenType.LBRACE, value);
                break;
            case "}":
                token = new Token(TokenType.RBRACE, value);
                break;
            case " ":
                return;
            default:
                token = new Token(TokenType.TEXT, value);
        }
        tokens.add(token);
    }
}
