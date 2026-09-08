package container;

import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;  // also exist with assertFalse

public class TestIntFIFO  {
    @Test
    public void test_emptyCreation()  {
        IntFIFO fifo = new IntFIFO(10);
        assertTrue(fifo.isEmpty() );
        assertEquals(0,fifo.size());
    }

    @Test
    public void test_elementInsert() {
        IntFIFO fifo = new IntFIFO(10);
        assertTrue(fifo.insertElement(0) );
        assertEquals(1,fifo.size());
    }
    @Test
    public void test_elementCheck() {
        IntFIFO fifo = new IntFIFO(10);
        assertTrue(fifo.insertElement(0) );
        assertEquals(0,fifo.element());
    }

    @Test
    public void test_elementPop() {
        IntFIFO fifo = new IntFIFO(10);
        assertTrue(fifo.insertElement(0) );
        assertTrue(fifo.insertElement(1) );
        assertEquals(2,fifo.size());
        assertEquals(0,fifo.popElement());
        assertEquals(1,fifo.size());
    }

    @Test
    public void test_resize() {
        IntFIFO fifo = new IntFIFO(2);
        assertTrue(fifo.insertElement(0) );
        assertTrue(fifo.insertElement(1) );
        assertTrue(fifo.insertElement(2) );
        assertTrue(fifo.insertElement(3) );
        assertTrue(fifo.insertElement(4) );
        assertEquals(5,fifo.size());
        assertEquals(0,fifo.popElement());
        assertEquals(1,fifo.popElement());
    }

    @Test

    public void test_circular() {
        IntFIFO fifo = new IntFIFO(2);
        assertTrue(fifo.insertElement(0) );
        assertTrue(fifo.insertElement(1) );
        assertTrue(fifo.insertElement(2) );
        fifo.popElement();
        fifo.popElement();
        fifo.insertElement(3);
        fifo.insertElement(4);
        assertEquals(3,fifo.size());
        assertEquals(2,fifo.popElement());
        assertEquals(3,fifo.popElement());
        assertEquals(4,fifo.popElement());
        assertEquals(0,fifo.size());
    }
}

