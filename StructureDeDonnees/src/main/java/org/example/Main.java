package org.example;

import container.IntFIFO;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {
        IntFIFO test_fifo = new IntFIFO(4);
        System.out.println(test_fifo.isEmpty());
        System.out.println(test_fifo.size());
        System.out.println(test_fifo.insertElement(0));
        System.out.println(test_fifo.isEmpty());
        System.out.println(test_fifo.insertElement(1));
        System.out.println(test_fifo.insertElement(2));
        System.out.println(test_fifo.insertElement(3));
        System.out.println(test_fifo.element());
        System.out.println(test_fifo.size());
        System.out.println(test_fifo.popElement());
        System.out.println(test_fifo.size());
        System.out.println(test_fifo.element());
        System.out.println(test_fifo.insertElement(4));
        System.out.println(test_fifo.size());
        System.out.println(test_fifo.insertElement(5));
        System.out.println(test_fifo.size());
        System.out.println(test_fifo.popElement());
    }
}
