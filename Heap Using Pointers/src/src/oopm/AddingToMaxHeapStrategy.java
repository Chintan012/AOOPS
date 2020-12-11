package src.oopm;

public class AddingToMaxHeapStrategy implements AddingStrategy {

    @Override
    public Integer compareNodeValue(Node rootNode, Integer value) {
        return rootNode.compareNodeValue(value, rootNode.getValue());
    }

}
