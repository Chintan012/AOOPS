package src.oopm;

import java.util.*;
import java.util.function.Consumer;

public class Heap implements Queue<Integer>, HeapDecorator {

    protected Node rootNode;
    private AddingStrategy strategy;
    private int size = 0;
    private int initialCapacity = 10;
    private int offset = 10;
    private transient Object[] heap = new Object[initialCapacity];

    public Heap(AddingStrategy addByOrderX) {
        rootNode = NullNode.getInstance();
        strategy = addByOrderX;
    }

    @Override
    public String toString() {
        Iterator<Integer> iterator = this.iterator();
        StringBuffer string1 = new StringBuffer();
        string1.append("{");
        string1.append(iterator.next());
        while (iterator.hasNext()) {
            string1.append(",");
            string1.append(iterator.next());
        }
        string1.append("}");
        return string1.toString();
    }

    private void toStrings(Node node, StringBuffer string) {

        if (node instanceof HeapNode) {
            string.append(node.getValue());
            if (node.leftNode instanceof HeapNode) {
                string.append(", ");
                toStrings(node.leftNode, string);
            }
            if (node.rightNode instanceof HeapNode) {
                string.append(", ");
                toStrings(node.rightNode, string);
            }
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ExternalHeapIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(heap, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        if (rootNode instanceof HeapNode) {
            traverseInOrder(rootNode, action);
        }
    }

    private void traverseInOrder(Node rootNode, Consumer<? super Integer> action) {
        if (rootNode instanceof HeapNode) {
            traverseInOrder(rootNode.leftNode, action);
            action.accept(rootNode.getValue());
            traverseInOrder(rootNode.rightNode, action);
        }
    }

    @Override
    public boolean add(Integer value) {
        this.rootNode = rootNode.add(value, strategy);
        heap[size] = value;
        size += 1;
        if (size > initialCapacity) {
            initialCapacity = initialCapacity + offset;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(Integer value) {
        return this.add(value);
    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public Integer poll() {
        return null;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        return null;
    }

    private class ExternalHeapIterator implements Iterator<Integer> {

        Stack<Node> stackOfHeapElements;
        Node root;

        public ExternalHeapIterator() {
            root = rootNode;
            stackOfHeapElements = new Stack<Node>();
            while (root instanceof HeapNode) {
                stackOfHeapElements.push(root);
                root = root.leftNode;
            }
        }

        @Override
        public boolean hasNext() {
            return !stackOfHeapElements.isEmpty();
        }

        @Override
        public Integer next() {
            Node root = stackOfHeapElements.pop();
            int nextElement = root.getValue();
            if (root.rightNode instanceof HeapNode) {
                root = root.rightNode;
                while (root instanceof HeapNode) {
                    stackOfHeapElements.push(root);
                    root = root.leftNode;
                }
            }
            return nextElement;
        }
    }

}
