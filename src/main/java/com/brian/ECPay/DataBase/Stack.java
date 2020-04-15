package com.brian.ECPay.DataBase;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack<E> {
    private Deque<E> deque = new ArrayDeque<>();
    
    public boolean push(E elem) {
        return deque.offerLast(elem);
    }

    
    public E pop() {
        return deque.pollLast();
    }
    
    public E peek() {
        return deque.peekLast();
    }
    
    public int size() {
        return deque.size();
    }
}
