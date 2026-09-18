package container;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class GenPriorityQueueCmp<E> implements Queue<E> {
    private Object[] tab;
    private Comparator<? super E> c;
    private int e;

    public GenPriorityQueueCmp(int capacity, Comparator<? super E> comparator ) {
        tab = new Object[capacity];
        e=0;
        c=comparator;
    }

    private void resize() {
        int l = this.size();
        Object[] newTab = new Object[max(2,l*2)];
        for (int i=0; i<l; i++) {
            newTab[i]=(E) tab[i];
        }
        e = l;
        tab = newTab;
    }

    private void swap(int i ,int j) {
        E temp= (E) tab[i];
        tab[i]=tab[j];
        tab[j]=temp;
    }

    private int highest(int i) {
        int highest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < e && (c.compare((E)tab[left],(E) tab[highest]) > 0)) {
            highest = left;
        }

        if (right < e && (c.compare((E)tab[right],(E) tab[highest]) > 0)) {
            highest = right;
        }

        return highest;
    }

    private void montee(int i){
        int child = i;
        int root = (i-1)/2;
        while (child>0 && c.compare((E)tab[child],(E) tab[root]) > 0) {
            swap(child,root);
            child=root;
            root=(child-1)/2;
        }
    }

    private void descente(int i){
        int h = highest(i);
        while (h!=i){
            swap(i,h);
            i=h;
            h = highest(i);
        }
    }

    @Override
    public boolean insertElement(E elt) {
        if (e==tab.length) {
            this.resize();
        }
        tab[e]=elt;
        montee(e);
        e+=1;
        return true;
    }

    @Override
    public E element() {
        if (e==0) {
            throw new NoSuchElementException();
        }
        return (E) tab[0];
    }

    @Override
    public E popElement() {
        if (e==0) {
            throw new NoSuchElementException();
        }
        E elt = (E) tab[0];
        e-=1;
        swap(0,e);
        tab[e]=null;
        descente(0);
        return elt;
    }

    @Override
    public boolean isEmpty() {
        return e==0;
    }

    @Override
    public int size() {
        return e;
    }

    @Override
    public Iterator iterator() {
        return new GenPriorityQueueCmp.GenPriorityQueueCmpIterator();
    }

    class GenPriorityQueueCmpIterator implements Iterator<E> {
        private Object[] data;
        private int i = 0;
        private int e;

        GenPriorityQueueCmpIterator() {
            data = GenPriorityQueueCmp.this.tab;
            e= GenPriorityQueueCmp.this.e;
        }

        public boolean hasNext () {
            return i<e;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = (E) data[i];
            i++;
            return val;
        }
    }
}
