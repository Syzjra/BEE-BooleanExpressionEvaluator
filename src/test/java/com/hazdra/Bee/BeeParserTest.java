package test.java.com.hazdra.Bee;


import main.java.com.hazdra.Bee.BeeParser;
import main.java.com.hazdra.Bee.models.BeeNode;
import main.java.com.hazdra.Bee.models.BeeNodeType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final public class BeeParserTest {

    private final BeeParser parser = new BeeParser();

    @Test
    void testSingleValue() {
        BeeNode node = parser.parse("1");
        assertEquals(BeeNodeType.VALUE, node.getType());
        assertEquals("1", node.getValue());
    }

    @Test
    void testSimpleAnd() {
        BeeNode node = parser.parse("1 AND 2");
        assertEquals(BeeNodeType.AND, node.getType());
        assertEquals("1", node.getLeft().getValue());
        assertEquals("2", node.getRight().getValue());
    }

    @Test
    void testSimpleOr() {
        BeeNode node = parser.parse("3 OR 4");
        assertEquals(BeeNodeType.OR, node.getType());
        assertEquals("3", node.getLeft().getValue());
        assertEquals("4", node.getRight().getValue());
    }

    @Test
    void testNestedExpression() {
        BeeNode node = parser.parse("( 1 AND 2 ) OR 3");

        assertEquals(BeeNodeType.OR, node.getType());
        assertEquals(BeeNodeType.AND, node.getLeft().getType());
        assertEquals("1", node.getLeft().getLeft().getValue());
        assertEquals("2", node.getLeft().getRight().getValue());
        assertEquals("3", node.getRight().getValue());
    }

    @Test
    void testChainedAnd() {
        BeeNode node = parser.parse("1 AND 2 AND 3");

        assertEquals(BeeNodeType.AND, node.getType());
        assertEquals("3", node.getRight().getValue());

        assertEquals(BeeNodeType.AND, node.getLeft().getType());
        assertEquals("1", node.getLeft().getLeft().getValue());
        assertEquals("2", node.getLeft().getRight().getValue());
    }

    @Test
    void testDefaultOperatorWhenMissing() {
        BeeNode node = parser.parse("1 AND 2");
        assertEquals(BeeNodeType.AND, node.getType()); // fallback operator
        assertEquals("1", node.getLeft().getValue());
        assertEquals("2", node.getRight().getValue());
    }

    @Test
    void testEmptyExpressionReturnsNull() {
        BeeNode node = parser.parse("");
        assertNull(node);
    }
}
