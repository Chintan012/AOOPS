package src.oopm;

public abstract class Node {

    Node leftNode;
    Node rightNode;
    private Integer value;

    protected Node(Integer value) {
        this(value, null, null);
    }

    protected Node(Integer value, Node leftNode, Node rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    protected Integer getValue() {
        return this.value;
    }

    protected void setValue(Integer value) {
        this.value = value;
    }

    protected abstract Integer compareNodeValue(Integer nodeValue, Integer value);

    protected abstract Integer getHeight(Node rootNode);

    protected abstract Node add(Integer value, AddingStrategy addingStrategy);
}
