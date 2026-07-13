import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Formatter {
    Stylesheet stylesheet;
    Map<String, Integer> priorityMap;

    public Formatter(Stylesheet stylesheet, Map<String, Integer> priorityMap) {
        this.stylesheet = stylesheet;
        this.priorityMap = priorityMap;
    }
    
    public void format() {
        // format each rule in stylesheet to comply with the map given
        Comparator<Declaration> comparator = makeComparator();
        for (Rule rule : stylesheet.getRules()) {
            List<Declaration> declarations = rule.getDeclarations();
            declarations.sort(comparator);
        }
    }

    private Comparator<Declaration> makeComparator() {
        Comparator<Declaration> comparator = new Comparator<>() {
            @Override
            public int compare(Declaration d1, Declaration d2) {
                int priority1 = priorityMap.getOrDefault(d1.getProperty(), Integer.MAX_VALUE);

                int priority2 = priorityMap.getOrDefault(d2.getProperty(), Integer.MAX_VALUE);

                return Integer.compare(priority1, priority2);
            }
        };
        return comparator;
    }
}
