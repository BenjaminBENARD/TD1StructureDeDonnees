package container;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class GenSet<E extends Comparable<E>> implements SetContainer<E> {

    private static class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E element;
        public Node(E elt, Node<E> fg, Node<E> fd) {
            element = elt;
            left = fg;
            right = fd;
        }

    }

    private Node<E> tree;
    private int size;

    public GenSet() {
        tree = new Node(null,null,null);
        size = 0;
    }

    @Override
    public boolean insertElement(E e) {
        Node<E> current = tree;
        while (current.element != null) {
            if (e.compareTo(current.element) < 0) { //il faut aller à gauche
                current = current.left;
            } else { //il faut aller à droite, par convention en cas d'égalité on va à droite
                current = current.right;
            }
        }
        current.element = e;
        current.left = new Node<E>(null,null,null);
        current.right = new Node<E>(null,null,null);
        ++this.size;
        return true;
    }

    @Override
    public boolean contains(E e) {
        Node<E> current = tree;
        while (current.element != null && current.element != e) {
            if (e.compareTo(current.element) < 0) { //il faut aller à gauche
                current = current.left;
            } else { //il faut aller à droite
                current = current.right;
            }
        }
        return (current.element == e);
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new GenSet.GenSetTreeIterator();
    }

    class GenSetTreeIterator implements Iterator<E> {
        private Stack<Node<E>> stack = new Stack();
        private Node<E> current;

        GenSetTreeIterator() {
            current = GenSet.this.tree;
            while (current.element != null) {
                stack.add(current);
                current = current.left;
            }
        }

        public boolean hasNext () {
            return ! stack.isEmpty();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = stack.pop();
            E elt = current.element;
            if (current.right.element != null) { //fils droit existe
                current = current.right;
                while (current.element != null) {
                    stack.add(current);
                    current = current.left ;
                }
            }
            return elt;
        }

    }

}
