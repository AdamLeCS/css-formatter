import java.util.ArrayList;
import java.util.List;

public class Stylesheet {
    List<Rule> rules;

    public Stylesheet() {
        rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

}
