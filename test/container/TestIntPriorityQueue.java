package container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestIntPriorityQueue {
    @Test
    public void test_emptyCreation()  {
        IntPriorityQueue fifo = new IntPriorityQueue(10);
        assertTrue(fifo.isEmpty() );
        assertEquals(0,fifo.size());
    }

    @Test
    public void test_elementCheck() {
        IntPriorityQueue priorityQueue = new IntPriorityQueue(10);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(0,priorityQueue.element());
    }
    
    @Test
    public void test_elementInsert() {
        IntPriorityQueue priorityQueue = new IntPriorityQueue(10);
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(1,priorityQueue.size());
        assertTrue(priorityQueue.insertElement(1) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.element());
    }

    @Test
    public void test_elementPop() {
        IntPriorityQueue priorityQueue = new IntPriorityQueue(10);
        assertTrue(priorityQueue.insertElement(1) );
        assertTrue(priorityQueue.insertElement(0) );
        assertEquals(2,priorityQueue.size());
        assertEquals(1,priorityQueue.popElement());
        assertEquals(1,priorityQueue.size());
        assertEquals(0,priorityQueue.popElement());
    }

    @Test
    public void test_resize() {
        IntPriorityQueue priorityQueue = new IntPriorityQueue(2);
        assertTrue(priorityQueue.insertElement(0) );
        assertTrue(priorityQueue.insertElement(1) );
        assertTrue(priorityQueue.insertElement(2) );
        assertTrue(priorityQueue.insertElement(3) );
        assertTrue(priorityQueue.insertElement(4) );
        assertEquals(5,priorityQueue.size());
        assertEquals(4,priorityQueue.popElement());
        assertEquals(3,priorityQueue.popElement());
    }

}