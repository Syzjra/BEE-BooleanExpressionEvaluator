package main.java.com.Syzjra.Bee.models;

final public class BeeNode {
    private BeeNodeType type;
    private BeeNode left;
    private BeeNode right;
    private String value; // usado só se for VALUE

    public BeeNode(BeeNodeType type, BeeNode left, BeeNode right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public BeeNode(String value) {
        this.type = BeeNodeType.VALUE;
        this.value = value;
    }

    public BeeNodeType getType() {
        return type;
    }

    public void setType(BeeNodeType type) {
        this.type = type;
    }

    public BeeNode getLeft() {
        return left;
    }

    public void setLeft(BeeNode left) {
        this.left = left;
    }

    public BeeNode getRight() {
        return right;
    }

    public void setRight(BeeNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
