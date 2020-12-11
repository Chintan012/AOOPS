package src.oopm;

import java.util.Iterator;

public abstract class AddDecoratingParameters implements HeapDecorator {

    private final HeapDecorator addedDecoratingParameters;

    public AddDecoratingParameters(HeapDecorator heapDecorator) {
        this.addedDecoratingParameters = heapDecorator;
    }

    @Override
    public Iterator<Integer> iterator() {
        return addedDecoratingParameters.iterator();
    }

    @Override
    public Object[] toArray() {
        return addedDecoratingParameters.toArray();
    }

    @Override
    public String toString() {
        return addedDecoratingParameters.toString();
    }
}
