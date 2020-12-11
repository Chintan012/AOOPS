package src.oopm;

import java.util.Iterator;

public interface HeapDecorator {

    Iterator<Integer> iterator();

    String toString();

    Object[] toArray();
}
