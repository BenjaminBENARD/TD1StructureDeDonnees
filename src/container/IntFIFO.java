package container;

import javax.management.InvalidAttributeValueException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class IntFIFO implements Queue<Integer> {

    private Integer[] tab;

    private int b;
    private int e;

    public IntFIFO (int capacity) {
        tab = new Integer[capacity+1];
        b=0;
        e=0;
    }

    private int next (int i) {
        if (i==tab.length-1) {
            return 0;
        } else {
            return i+1;
        }
    }

    private void resize() {
        int l = this.size();
        Integer[] newTab = new Integer[max(4,l*2)];
        for (int i=0; i<l; i++) {
            newTab[i]=popElement();
        }
        b = 0;
        e = l;
        tab = newTab;
    }

    @Override
    public boolean insertElement(Integer i) {

        if (((e+1) % tab.length) == b) {
            this.resize();
        }
        tab[e]=i;
        e=next(e);
        return true;
    }

    @Override
    public Integer element() {
        if (b==e) {
            throw new NoSuchElementException();
        }
        return tab[b];
    }

    @Override
    public Integer popElement() {
        if (b==e) {
            throw new NoSuchElementException();
        }
        Integer elt = tab[b];
        b=next(b);
        return elt;
    }

    @Override
    public boolean isEmpty() {
        return b==e;
    }

    @Override
    public int size() {
        if (e<b) {
            return tab.length+e-b;
        } else {
            return e-b;
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}