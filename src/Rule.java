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
}
