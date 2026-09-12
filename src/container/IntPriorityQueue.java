package container;

import javax.management.InvalidAttributeValueException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] tab;

    private int b;
    private int e;

    public IntPriorityQueue(int capacity) {
        tab = new Integer[capacity+1];
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

    private int highest(int i){
        //return the index of the highest element between the parent i and his 2 possible children
        int child = 2*i+1;
        if (child<e && tab[i]<tab[child]){
            if (child+1<e && tab[child]<tab[child+1]){
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
        montee(e);
        e+=1;
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