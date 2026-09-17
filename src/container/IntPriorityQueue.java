package container;

import javax.management.InvalidAttributeValueException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] tab;

    private int e;

    public IntPriorityQueue(int capacity) {
        tab = new Integer[capacity];
        e=0;
    }

    private void resize() {
        int l = this.size();
        Integer[] newTab = new Integer[max(2,l*2)];
        for (int i=0; i<l; i++) {
            newTab[i]=tab[i];
        }
        e = l;
        tab = newTab;
    }

    private void swap(int i ,int j) {
        Integer temp=tab[i];
        tab[i]=tab[j];
        tab[j]=temp;
    }

    private int highest(int i) {
        int highest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < e && tab[left] > tab[highest]) {
            highest = left;
        }

        if (right < e && tab[right] > tab[highest]) {
            highest = right;
        }

        return highest;
    }

    private void montee(int i){
        int child = i;
        int root = (i-1)/2;
        while (child>0 && tab[child]>tab[root]) {
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
    public boolean insertElement(Integer elt) {
        if (e==tab.length) {
            this.resize();
        }
        tab[e]=elt;
        e+=1;
        montee(e-1);
        return true;
    }

    @Override
    public Integer element() {
        if (e==0) {
            throw new NoSuchElementException();
        }
        return tab[0];
    }

    @Override
    public Integer popElement() {
        if (e==0) {
            throw new NoSuchElementException();
        }
        Integer elt = tab[0];
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
        return new IntPriorityQueueIterator();
    }

    class IntPriorityQueueIterator implements Iterator<Integer> {
        private Integer[] data;
        private int i = 0;
        private int e;

        IntPriorityQueueIterator() {
            data = IntPriorityQueue.this.tab;
            e= IntPriorityQueue.this.e;
        }

        public boolean hasNext () {
            return i<e;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer val = data[i];
            i++;
            return val;
        }
    }
}