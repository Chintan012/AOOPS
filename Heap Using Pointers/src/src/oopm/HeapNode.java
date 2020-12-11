package src.oopm;

public class HeapNode extends Node {


    protected HeapNode(Integer value) {
        super(value, NullNode.getInstance(), NullNode.getInstance());
    }

    @Override
    protected Integer compareNodeValue(Integer nodeValue, Integer value) {
        if (nodeValue > value) {
            return 1;
        } else if (nodeValue < value) {
            return -1;
        } else return 0;
    }

    @Override
    protected Integer getHeight(Node rootNode) {
        int leftHeight = rootNode.getHeight(rootNode.leftNode);
        int rightHeight = rootNode.getHeight(rootNode.rightNode);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    protected Node add(Integer value, AddingStrategy addingStrategy) {
        if (addingStrategy.compareNodeValue(this, value) <= 0) {
            int leftHeight = this.getHeight(this.leftNode);
            int rightHeight = this.getHeight(this.rightNode);

            if (leftHeight <= rightHeight) {
                this.leftNode = this.leftNode.add(value, addingStrategy);
            } else if (leftHeight > rightHeight) {
                this.rightNode = this.rightNode.add(value, addingStrategy);
            }
        } else if (addingStrategy.compareNodeValue(this, value) > 0) {
            int valueToBePassedDown = this.getValue();
            this.setValue(value);
            add(valueToBePassedDown, addingStrategy);
        }
        return this;
    }
}
