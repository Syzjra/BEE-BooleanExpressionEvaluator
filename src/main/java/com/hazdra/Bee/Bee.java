package main.java.com.hazdra.Bee;
import main.java.com.hazdra.Bee.models.BeeNode;
import main.java.com.hazdra.Bee.models.BeeResult;

import java.util.Set;

final public class Bee {

    public BeeResult evaluate(BeeNode beeNode, Set<String> completed) {
        return switch (beeNode.getType()) {
            case VALUE -> onValueEvaluation(completed.contains(beeNode.getValue()));
            case AND -> onAndEvaluation(beeNode, completed);
            case OR -> onOrEvaluation(beeNode, completed);
            default -> throw new IllegalStateException("Unexpected beeNode type: " + beeNode.getType());
        };
    }

    private static BeeResult onValueEvaluation(boolean completed) {
        return new BeeResult(completed ? 1 : 0, 1);
    }

    private BeeResult onAndEvaluation(BeeNode beeNode, Set<String> completed) {
        BeeResult leftAnd = evaluate(beeNode.getLeft(), completed);
        BeeResult rightAnd = evaluate(beeNode.getRight(), completed);
        return new BeeResult(
                leftAnd.getDone() + rightAnd.getDone(),
                leftAnd.getTotal() + rightAnd.getTotal()
        );
    }

    private BeeResult onOrEvaluation(BeeNode beeNode, Set<String> completed) {
        BeeResult leftOr = evaluate(beeNode.getLeft(), completed);
        BeeResult rightOr = evaluate(beeNode.getRight(), completed);

        double bestRate = Math.max(leftOr.getRate(), rightOr.getRate());

        return new BeeResult(bestRate, 1);
    }

}