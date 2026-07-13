import java.util.List;

public class Parser {
    List<Token> tokens;
    Stylesheet stylesheet;
    int currIndex;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        stylesheet = new Stylesheet();
        currIndex = 0;
    }

    public Stylesheet getStylesheet() {
        return stylesheet;
    }
    
    public void parse() {
        // parse through tokens list and make objects for formatter
        
        while (currIndex < tokens.size()) {
            parseRule();           
        }
    }

    public void parseRule() {
        // get selector
        String selector = parseSelector();
        
        // create rule
        Rule newRule = new Rule(selector);

        // get declarations until rbrace is reached
        while (tokens.get(currIndex).getTokenType() != TokenType.RBRACE) {
            Declaration newDeclaration = parseDeclaration();
            newRule.addDeclaration(newDeclaration);
        }
        // exits loop at rbrace token, so move forward one more
        currIndex++;

        stylesheet.addRule(newRule);
    }

    public String parseSelector() {
        StringBuilder stb = new StringBuilder();
        Token currToken = tokens.get(currIndex);
        while (currToken.getTokenType() != TokenType.LBRACE) {
            switch (currToken.getTokenType()) {
                case TEXT:
                    stb.append(" " + currToken.getTokenValue());
                    break;
                case COMMA:
                    stb.append(",");
                    break;
                default:
                    break;
            }
            currIndex++;
            currToken = tokens.get(currIndex);
        }
        // exits on an lbrace, so move to next token
        currIndex++;
        // has extra space at the beginning from above logic
        stb.delete(0, 1);
        return stb.toString();
    }

    public Declaration parseDeclaration() {
        Token currToken = tokens.get(currIndex);
        String property = currToken.getTokenValue();
        String value;
        currIndex += 2;
        currToken = tokens.get(currIndex);
        
        StringBuilder stb = new StringBuilder();
        while (currToken.getTokenType() != TokenType.SEMICOLON) {
            switch(currToken.getTokenType()) {
                case TEXT:
                    stb.append(" " + currToken.getTokenValue());
                    break;
                case COMMA:
                    stb.append(",");
                    break;
                default:
                    break;
            }
            currIndex++;
            currToken = tokens.get(currIndex);
        }
        // exits on a semicolon, so move to next index
        currIndex++;
        // remove extra space at beginning
        stb.delete(0, 1);
        value = stb.toString();
        Declaration newDeclaration = new Declaration(property, value);
        return newDeclaration;
    }

}
