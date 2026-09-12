package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class GenPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private Object[] tab;

    private int e;

    public GenPriorityQueue(int capacity) {
        tab = new Object[capacity+1];
        e=0;
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

    private int highest(int i){
        //return the index of the highest element between the parent i and his 2 possible children
        int child = 2*i+1;
        if (child<e && ((E)tab[i]).compareTo((E) tab[child]) < 0) {
            if (child+1<e && ((E)tab[child]).compareTo((E) tab[child+1]) < 0){
                return child+1;
            } else {
                return child;
            }
        } else {
            return i;
        }
    }

    private void montee(int i){
        int child = i;
        int root = (i-1)/2;
        while (child>0 && ((E)tab[child]).compareTo((E) tab[root]) < 0) {
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
        return null;
    }
}