package container;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TestGenPriorityQueueCmp {

    public class MyComparator implements Comparator<Integer> {

        public int compare(Integer a, Integer b) {
            if (a<b) {
                return -1;
            } else if (a==b) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public MyComparator c = new MyComparator();

    @Test
    public void test_emptyCreation()  {
        GenPriorityQueueCmp<Integer> fifo = new GenPriorityQueueCmp<Integer>(10,c);
        assertTrue(fifo.isEmpty() );
        assertEquals(0,fifo.size());
    }


    @Test
    public void test_elementCheck() {
        GenPriorityQueueCmp<Integer> priorityQueue = new GenPriorityQueueCmp<Integer>(10,c);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(0,priorityQueue.element());
    }

    @Test
    public void test_elementInsert() {
        GenPriorityQueueCmp<Integer> priorityQueue = new GenPriorityQueueCmp<Integer>(10,c);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(1,priorityQueue.size());
        assertTrue(priorityQueue.insertElement(1) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.element());
    }

    @Test
    public void test_elementPop() {
        GenPriorityQueueCmp<Integer> priorityQueue = new GenPriorityQueueCmp<Integer>(10,c);
        assertTrue(priorityQueue.insertElement(1) );
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.popElement());
        assertEquals(1,priorityQueue.size());
        assertEquals(0,priorityQueue.popElement());
    }

    @Test
    public void test_resize() {
        GenPriorityQueueCmp<Integer> priorityQueue = new GenPriorityQueueCmp<Integer>(2,c);
        assertTrue(priorityQueue.insertElement(0) );
        assertTrue(priorityQueue.insertElement(1) );
        assertTrue(priorityQueue.insertElement(2) );
        assertTrue(priorityQueue.insertElement(3) );
        assertTrue(priorityQueue.insertElement(4) );
        assertTrue(priorityQueue.insertElement(5) );
        assertEquals(6,priorityQueue.size());
        assertEquals(5,priorityQueue.popElement());
        assertEquals(5,priorityQueue.size());
        assertTrue(priorityQueue.insertElement(5) );
        assertEquals(6,priorityQueue.size());

    }
}