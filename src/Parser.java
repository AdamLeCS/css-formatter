import java.util.List;
import java.lang.StringBuilder;

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
        
        /*while (currIndex < tokens.size()) {
            parseRule();           
        } */

        for(int i = 0; i < 3; i++) {
            parseRule();
        }
    }

    public void parseRule() {
        // get selector
        String selector = parseSelector();
        
        // create rule
        Rule newRule = new Rule(selector);

        // get declarations

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
        // has extra space at the beginning from above logic
        stb.delete(0, 1);
        System.out.println(stb.toString());
        return stb.toString();
    }

    public Declaration parseDeclaration() {

        Declaration newDeclaration = new Declaration("", "");
        return newDeclaration;
    }

}
