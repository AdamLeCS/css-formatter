import java.util.ArrayList;
import java.util.List;

public class Rule {
    private List<Declaration> declarations;
    private String ruleName;

    public Rule(String ruleName) {
        declarations = new ArrayList<>();
        this.ruleName = ruleName;
    }

    public void addDeclaration(Declaration declaration) {
        declarations.add(declaration);
    }

    public String getRuleName() {
        return ruleName;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }
    
    public String returnStylesheet() {
        // returns the stylesheet object as a correctly formatted string using string builder
        return "";
    }

    public void print() {
        System.out.println(ruleName + " {");
        for (Declaration declaration : declarations) {
            declaration.print();
        }
        System.out.println("}");
    }

    public String getRule() {
        StringBuilder stb = new StringBuilder();
        stb.append(ruleName).append(" {\n");
        for (Declaration dec : declarations) {
            stb.append("\t").append(dec.getDeclaration()).append("\n");
        }
        stb.append("}\n");
        return stb.toString();
    }
}
