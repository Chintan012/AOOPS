package src.oopm;

import java.util.Iterator;

public class OddFilter implements Iterator<Integer> {

    private Iterator<Integer> oddIterator;
    int oddElement;

    public OddFilter(Iterator<Integer> iterator) {
        this.oddIterator = iterator;
    }

    @Override
    public boolean hasNext() {
        while(oddIterator.hasNext()) {
            oddElement = oddIterator.next();
            if(oddElement % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return oddElement;
    }

}
