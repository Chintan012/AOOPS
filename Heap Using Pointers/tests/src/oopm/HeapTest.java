package src.oopm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapTest {

    public void checkAdd_In_MinHeap(List<Integer> elementsToBeInserted,
                                        List<Integer> expectedIteratedMinHeapOutput) {

        //Arrange and act
        Heap constructMinHeapForTesting = new Heap(new AddingToMinHeapStrategy());
        for(int element : elementsToBeInserted) {
            constructMinHeapForTesting.add(element);
        }
        Iterator<Integer> minHeapIterator = constructMinHeapForTesting.iterator();
        List<Integer> observedExternalIteratorOutput = new ArrayList<>();
        while(minHeapIterator.hasNext()) {
            int value = minHeapIterator.next();
            observedExternalIteratorOutput.add(value);
        }

        List<Integer> observedInternalIteratorOutput = new ArrayList<>();
        constructMinHeapForTesting.forEach(integer -> observedInternalIteratorOutput.add(integer));

        //Assert for external iterator
        assertEquals(expectedIteratedMinHeapOutput, observedExternalIteratorOutput);

        //Assert for internal iterator
        assertEquals(expectedIteratedMinHeapOutput, observedInternalIteratorOutput);
    }

    public void checkAdd_In_MaxHeap(List<Integer> elementsToBeInserted,
                                    List<Integer> expectedIteratedMaxHeapOutput) {

        //Arrange and act
        Heap constructMaxHeapForTesting = new Heap(new AddingToMaxHeapStrategy());
        for(int element : elementsToBeInserted) {
            constructMaxHeapForTesting.add(element);
        }
        Iterator<Integer> maxHeapIterator = constructMaxHeapForTesting.iterator();
        List<Integer> observedExternalIeratorOutput = new ArrayList<>();
        while(maxHeapIterator.hasNext()) {
            observedExternalIeratorOutput.add(maxHeapIterator.next());
        }

        List<Integer> observedInternalIteratorOutput = new ArrayList<>();
        constructMaxHeapForTesting.forEach(integer -> observedInternalIteratorOutput.add(integer));

        //Assert for external iterator
        assertEquals(expectedIteratedMaxHeapOutput, observedExternalIeratorOutput);

        //Assert for internal iterator
        assertEquals(expectedIteratedMaxHeapOutput, observedInternalIteratorOutput);
    }

    public void check_OddHeapDecorator_In_MinHeap(List<Integer> elementsToBeInserted,
                                    List<Integer> expectedIteratedHeapDecoratorOutput) {

        //Arrange and act
        Heap minHeapForOddHeapDecorator = new Heap(new AddingToMinHeapStrategy());

        for(int element : elementsToBeInserted) {
            minHeapForOddHeapDecorator.add(element);
        }

        HeapDecorator oddElements = new OddHeapDecorator(minHeapForOddHeapDecorator);

        Iterator<Integer> oddElementsIterator = oddElements.iterator();
        List<Integer> oddHeapDecoratorExternalIterator = new ArrayList<>();
        while(oddElementsIterator.hasNext()) {
            oddHeapDecoratorExternalIterator.add(oddElementsIterator.next());
        }

//        List<Integer> oddHeapDecoratorInternalIterator = new ArrayList<>();
//        minHeapForOddHeapDecorator.forEach(integer -> oddHeapDecoratorInternalIterator.add(integer));

//        Assert for external iterator
        assertEquals(expectedIteratedHeapDecoratorOutput, oddHeapDecoratorExternalIterator);

        //Assert for internal iterator
//        assertEquals(oddHeapDecoratorInternalIterator, expectedIteratedHeapDecoratorOutput);
    }

    /**
     * Tests for minHeap add function
     */
    @Test
    public void addToMinHeap_Empty() {
        this.checkAdd_In_MinHeap(
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())
        );
    }

    @Test
    public void addToMinHeap_SingleElement() {
        this.checkAdd_In_MinHeap(
                new ArrayList<>(Arrays.asList(45)),
                new ArrayList<>(Arrays.asList(45))
        );
    }

    @Test
    public void addToMinHeap_InAscendingOrder() {
        this.checkAdd_In_MinHeap(
                new ArrayList<>(Arrays.asList(2, 5, 16, 20, 30, 45)),
                new ArrayList<>(Arrays.asList(20, 5, 45, 2, 30, 16))
        );
    }

    @Test
    public void addToMinHeap_InDescendingOrder() {
        this.checkAdd_In_MinHeap(
                new ArrayList<>(Arrays.asList(45, 30, 20, 16, 5, 2)),
                new ArrayList<>(Arrays.asList(45, 5, 20, 2, 30, 16))
        );
    }

    @Test
    public void addToMinHeap_InRandomOrder() {
        this.checkAdd_In_MinHeap(
                new ArrayList<>(Arrays.asList(45, 5, 20, 16, 30, 2)),
                new ArrayList<>(Arrays.asList(45, 5, 16, 2, 30, 20))
        );
    }

    /**
     * Tests for maxHeap add function
     */
    @Test
    public void addToMaxHeap_InRandomOrder() {
        this.checkAdd_In_MaxHeap(
                new ArrayList<>(Arrays.asList(45, 5, 20, 16, 30, 2)),
                new ArrayList<>(Arrays.asList(5, 16, 2, 45, 20, 30))
        );
    }

    @Test
    public void addToMaxHeap_InAscendingOrder() {
        this.checkAdd_In_MaxHeap(
                new ArrayList<>(Arrays.asList(2, 5, 16, 20, 30, 45)),
                new ArrayList<>(Arrays.asList(2, 30, 16, 45, 5, 20))
        );
    }

    /**
     * Tests for HeapDecorator
     */

    @Test
    public void addToMinHeap_WithOddHeapDecorator() {
        this.check_OddHeapDecorator_In_MinHeap(
                new ArrayList<>(Arrays.asList(3, 5, 17, 20, 31, 45)),
                new ArrayList<>(Arrays.asList(5, 45, 3, 31, 17))
        );
    }

    /**
     * Tests for toArray and toString method
     */
    @Test
    public void toArrayTest() {

        //Arrange and Act
        Heap constructHeapForFunctions = new Heap(new AddingToMinHeapStrategy());
        constructHeapForFunctions.add(45);
        constructHeapForFunctions.add(31);
        constructHeapForFunctions.add(20);
        constructHeapForFunctions.add(16);
        constructHeapForFunctions.add(5);
        constructHeapForFunctions.add(2);

        Object[] expectedToArrayOutput = new Object[]{45, 31, 20, 16, 5, 2};
        Object[] observedToArrayOutput = constructHeapForFunctions.toArray();
        System.out.println(observedToArrayOutput);

        String expectedToStringOutput = "{45,31,20,16,5,2}";
        String observedToStringOutput = constructHeapForFunctions.toString();
        System.out.println(observedToStringOutput);

        //Assert for toArray function
        assertArrayEquals(expectedToStringOutput, observedToArrayOutput);

        //Assert for toString function
        assertEquals(expectedToStringOutput, observedToStringOutput);

    }

}