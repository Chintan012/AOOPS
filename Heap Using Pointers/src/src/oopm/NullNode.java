package src.oopm;

public class NullNode extends Node {

    static private Node instance;

    protected NullNode() {
        super(null);
    }

    public static Node getInstance() {
        if (instance == null) {
            instance = new NullNode();
        }
        return instance;
    }

    @Override
    protected Integer compareNodeValue(Integer nodeValue, Integer value) {
        return null;
    }

    @Override
    protected Integer getHeight(Node rootNode) {
        return 0;
    }

    @Override
    protected Node add(Integer value, AddingStrategy addingStrategy) {
        return new HeapNode(value);
    }
}
