package main.java.com.hazdra.Bee;


import main.java.com.hazdra.Bee.models.BeeNode;
import main.java.com.hazdra.Bee.models.BeeNodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

final public class BeeParser {
    private int index;
    private String[] tokens;

    public BeeNode parse(String expression) {
        expression = expression.replace("(", " ( ")
                .replace(")", " ) ")
                .trim();
        tokens = expression.split("\\s+");
        index = 0;
        return parseExpression();
    }

    private BeeNode parseExpression() {
        List<BeeNode> BeeNodes = new ArrayList<>();
        BeeNodeType currentOperator = null;

        while (index < tokens.length) {
            String token = tokens[index++];

            switch (token) {
                case "(":
                    BeeNodes.add(parseExpression());
                    break;
                case ")":
                    return combineBeeNodes(BeeNodes, currentOperator);
                case "AND":
                    currentOperator = BeeNodeType.AND;
                    break;
                case "OR":
                    currentOperator = BeeNodeType.OR;
                    break;
                default:
                    BeeNodes.add(new BeeNode(token));
                    break;
            }
        }

        return combineBeeNodes(BeeNodes, currentOperator);
    }

    private BeeNode combineBeeNodes(List<BeeNode> BeeNodes, BeeNodeType operator) {
        if (BeeNodes.isEmpty()) return null;
        if (BeeNodes.size() == 1) return BeeNodes.getFirst();

        BeeNode result = BeeNodes.getFirst();
        for (int i = 1; i < BeeNodes.size(); i++) {
            result = new BeeNode(operator != null ? operator : BeeNodeType.AND, result, BeeNodes.get(i));
        }
        return result;
    }
}
