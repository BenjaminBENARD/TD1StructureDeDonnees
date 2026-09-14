package container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenPriorityQueueTest {
    @Test
    public void test_emptyCreation()  {
        GenPriorityQueue<Integer> fifo = new GenPriorityQueue<Integer>(10);
        assertTrue(fifo.isEmpty() );
        assertEquals(0,fifo.size());
    }


    @Test
    public void test_elementCheck() {
        GenPriorityQueue<Integer> priorityQueue = new GenPriorityQueue<Integer>(10);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(0,priorityQueue.element());
    }

    @Test
    public void test_elementInsert() {
        GenPriorityQueue<Integer> priorityQueue = new GenPriorityQueue<Integer>(10);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(1,priorityQueue.size());
        assertTrue(priorityQueue.insertElement(1) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.element());
    }

    @Test
    public void test_elementPop() {
        GenPriorityQueue<Integer> priorityQueue = new GenPriorityQueue<Integer>(10);
        assertTrue(priorityQueue.insertElement(1) );
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.popElement());
        assertEquals(1,priorityQueue.size());
        assertEquals(0,priorityQueue.popElement());
    }

    @Test
    public void test_resize() {
        GenPriorityQueue<Integer> priorityQueue = new GenPriorityQueue<Integer>(2);
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