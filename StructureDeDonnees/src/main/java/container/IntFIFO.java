package container;

import java.util.Iterator;

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
        int l = tab.length;
        Integer[] newTab = new Integer[l*2];
        for (int i=0; i<l; i++) {
            newTab[i]=popElement();
        }
        b = 0;
        e = l;
        tab = newTab;
    }

    @Override
    public boolean insertElement(Integer i) {
        if (e == (b-1) % tab.length)  {
            resize();
        }
        tab[e]=i;
        e=next(e);
        return true;
    }

    @Override
    public Integer element() {
        assert b!=e;
        return tab[b];
    }

    @Override
    public Integer popElement() {
        assert b!=e;
        Integer elt = tab[b];
        b=next(b);
        return elt;
    }

    @Override
    public boolean isEmpty() {
        return b==e;
    }

    @Override
    public int size() { //à modif, pb quand e<b
            return (e-b+tab.length) % tab.length;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
