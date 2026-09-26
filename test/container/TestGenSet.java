package container;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TestGenSet {


    @Test
    void nouvelEnsembleEstVide() {
        GenSet<Integer> set = new GenSet<>(1);

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }


    @Test
    void insertionDunElement() {
        GenSet<Integer> set = new GenSet<>(1);

        assertTrue(set.insertElement(42));

        assertFalse(set.isEmpty());
        assertEquals(1, set.size());
        assertTrue(set.contains(42));
    }

    @Test
    void insertionDePlusieursElements() {
        GenSet<Integer> set = new GenSet<>(1);

        assertTrue(set.insertElement(8));
        assertTrue(set.insertElement(3));
        assertTrue(set.insertElement(10));
        assertTrue(set.insertElement(1));
        assertTrue(set.insertElement(6));

        assertEquals(5, set.size());

        assertTrue(set.contains(8));
        assertTrue(set.contains(3));
        assertTrue(set.contains(10));
        assertTrue(set.contains(1));
        assertTrue(set.contains(6));
    }

    @Test
    void containsElementPresent() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(10);
        set.insertElement(5);
        set.insertElement(15);

        assertTrue(set.contains(10));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
    }


    @Test
    void containsElementAbsent() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(10);
        set.insertElement(5);
        set.insertElement(15);

        assertFalse(set.contains(2));
        assertFalse(set.contains(7));
        assertFalse(set.contains(20));
    }


    @Test
    void valeursNegatives() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(0);
        set.insertElement(-10);
        set.insertElement(10);
        set.insertElement(-20);
        set.insertElement(-5);

        assertEquals(5, set.size());

        assertTrue(set.contains(-20));
        assertTrue(set.contains(-10));
        assertTrue(set.contains(-5));
        assertTrue(set.contains(0));
        assertTrue(set.contains(10));
    }


    @Test
    void iterateurEnsembleVide() {
        GenSet<Integer> set = new GenSet<>(1);

        Iterator<Integer> iterator = set.iterator();

        assertFalse(iterator.hasNext());
    }


    @Test
    void iterateurUnElement() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(42);

        Iterator<Integer> iterator = set.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(42, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void nextApresFinDoitLeverException() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(42);

        Iterator<Integer> iterator = set.iterator();

        iterator.next();

        assertThrows(
                NoSuchElementException.class,
                iterator::next
        );
    }

    @Test
    void iterateurParcoursDansOrdreCroissant() {
        GenSet<Integer> set = new GenSet<>(1);

        set.insertElement(8);
        set.insertElement(3);
        set.insertElement(10);
        set.insertElement(1);
        set.insertElement(6);
        set.insertElement(14);
        set.insertElement(4);
        set.insertElement(7);

        List<Integer> result = new ArrayList<>();

        for (Integer value : set) {
            result.add(value);
        }

        List<Integer> expected = List.of(
                1, 3, 4, 6, 7, 8, 10, 14
        );

        assertEquals(expected, result);
    }


}
