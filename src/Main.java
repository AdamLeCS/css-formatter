import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File orderFile = new File("order.txt");
        Scanner input = new Scanner(orderFile);

        // populate a map from order.txt file with priorities
        Map<String, Integer> priorityMap = new HashMap<>();
        int priority = 0;
        while (input.hasNextLine()) {
            priorityMap.put(input.nextLine(), priority++);
        }
        input.close();

        // read through each line in the css file and lex through it, adding tokens to
        // list
        List<Token> tokens = new ArrayList<>();
        Lexer lexer = new Lexer(tokens);
        File cssFile = new File("styles.css");
        input = new Scanner(cssFile);
        int j = 0;
        /*while (input.hasNextLine())*/ for (int i = 0; i < 15; i++) {
            String line = input.nextLine();
            lexer.processLine(line);
        }

        // parse through tokens list and create objects for the formatter
        Parser parser = new Parser(tokens);
        parser.parse();

        input.close();
    }
}
