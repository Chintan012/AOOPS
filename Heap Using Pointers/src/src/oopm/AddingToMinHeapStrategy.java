package src.oopm;

public class AddingToMinHeapStrategy implements AddingStrategy {

    @Override
    public Integer compareNodeValue(Node rootNode, Integer value) {
        return rootNode.compareNodeValue(rootNode.getValue(), value);
    }

}
