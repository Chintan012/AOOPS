package src.oopm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OddHeapDecorator extends AddDecoratingParameters {

    public OddHeapDecorator(HeapDecorator heapDecorator) {
        super(heapDecorator);
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> oddIterator = super.iterator();
        return new OddFilter(oddIterator);
    }

    @Override
    public Object[] toArray() {
        Integer[] elements = Arrays.asList(super.toArray()).toArray(new Integer[0]);
        return Arrays.stream(elements).filter(element -> element % 2 != 0).toArray();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = super.toString()
                .replace("{", "")
                .replace("}", "")
                .replaceAll("\\s", "")
                .split(",").clone();
        List<String> list = new ArrayList<>();
        stringBuilder.append("{");
        Arrays
                .stream(strings)
                .filter(element -> Integer.parseInt(element) % 2 != 0)
                .filter(element -> list.add(element))
                .toString();

        return stringBuilder.toString();
    }
}
