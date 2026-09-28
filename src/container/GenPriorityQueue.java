package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class GenPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private E tab[];

    private int e;

    public GenPriorityQueue(int capacity) {
        tab = (E[]) new Comparable[capacity];
        e=0;
    }

    private void resize() {
        int l = this.size();
        E[] newTab = (E[]) new Comparable[max(2,l*2)];
        for (int i=0; i<l; i++) {
            newTab[i]= tab[i];
        }
        e = l;
        tab = newTab;
    }

    private void swap(int i ,int j) {
        E temp= tab[i];
        tab[i]=tab[j];
        tab[j]=temp;
    }

    private int highest(int i) {
        int highest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < e && (tab[left]).compareTo( tab[highest]) > 0) {
            highest = left;
        }

        if (right < e && (tab[right]).compareTo( tab[highest]) > 0) {
            highest = right;
        }

        return highest;
    }

    private void montee(int i){
        int child = i;
        int root = (i-1)/2;
        while (child>0 && (tab[child]).compareTo( tab[root]) > 0) {
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
        return tab[0];
    }

    @Override
    public E popElement() {
        if (e==0) {
            throw new NoSuchElementException();
        }
        E elt = tab[0];
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
    public Iterator<E> iterator() {
        return new GenPriorityQueueIterator();
    }

    class GenPriorityQueueIterator implements Iterator<E> {
        private E[] data;
        private int i = 0;
        private int e;

        GenPriorityQueueIterator() {
            data = GenPriorityQueue.this.tab;
            e= GenPriorityQueue.this.e;
        }

        public boolean hasNext () {
            return i<e;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = data[i];
            i++;
            return val;
        }
    }
}