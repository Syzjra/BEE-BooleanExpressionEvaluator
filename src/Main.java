import main.java.com.hazdra.Bee.Bee;
import main.java.com.hazdra.Bee.models.BeeNode;
import main.java.com.hazdra.Bee.BeeParser;
import main.java.com.hazdra.Bee.models.BeeResult;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String exprString = "(123 AND 23 AND (53 OR 11) AND (09 OR 221)) OR 523";

        BeeParser parser = new BeeParser();
        BeeNode expr = parser.parse(exprString);

        Bee evaluator = new Bee();
        BeeResult result = evaluator.evaluate(expr, Set.of("23", "53", "221"));

        System.out.println("Taxa de completude = " + result.getRate());
    }
}