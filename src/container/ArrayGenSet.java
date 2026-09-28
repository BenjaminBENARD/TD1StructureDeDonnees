package container;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class ArrayGenSet<E extends Comparable<E>> implements SetContainer<E>{

    private Object[] tab;
    private int size;

    private void resize() {
        int l = this.tab.length;
        Object[] newTab = new Object[max(3,l*2)];
        for (int i=0; i<l; i++) {
            newTab[i]=(E) tab[i];
        }
        tab = newTab;
    }

    public ArrayGenSet(int capacity) {
        tab = new Object[capacity];
        size = 0;
    }

    @Override
    public boolean insertElement(E e) {
        int i = 0;
        while (tab[i] != null) {
            if (e.compareTo(((E) tab[i])) < 0) { //il faut aller à gauche
                i = 2 * i + 1;
            } else { //il faut aller à droite, par convention en cas d'égalité on va à droite
                i = 2 * i + 2;
            }
            if (i>=this.tab.length) {
                this.resize();
            }
        }
        this.tab[i]=e;
        ++this.size;
        return true;
    }

    @Override
    public boolean contains(E e) {
        int i = 0;
        while (i<this.tab.length && this.tab[i] != null && !((E)this.tab[i]).equals(e)) {
            if (e.compareTo(((E) tab[i])) < 0) { //il faut aller à gauche
                i = 2 * i + 1;
            } else { //il faut aller à droite
                i = 2 * i + 2;
            }
        }
        return (i<this.tab.length && ((E)this.tab[i]).equals(e));
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new ArrayGenSet.GenSetIterator();
    }

    class GenSetIterator implements Iterator<E> {
        final Object[] data;
        private int i;
        private int s;
        private int count=0;

        GenSetIterator() {
            data = ArrayGenSet.this.tab;
            s = ArrayGenSet.this.size;
            i = 0;
            while (2*i+1 < data.length && data[2*i+1] != null) {
                i = 2*i+1;
            }
        }

        public boolean hasNext () {
            return count<s;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = (E) data[i];
            if (2 * i + 2 < data.length && data[2 * i + 2] != null) { //fils droit existe
                i = 2 * i + 2;
                while (2 * i + 1 < data.length && data[2 * i + 1] != null) {
                    i = 2 * i + 1;
                }
            } else { // pas de fils droit
                if (i % 2 == 1) { // on est à gauche et le fils droit n'existe pas, on remonte
                    i = (i - 1) / 2;
                } else { //on est à droite, on doit forcément remonter jusqu'à arriver à gauche
                    while (i%2 == 0 && i>0) { // i>0 évite de boucler à l'infini si il n'y a qu'un élément
                        i = (i - 1) / 2;
                    }
                    i = (i - 1) / 2;
                }
            }
            ++count;
            return val;
        }
    }
}
