package linkedList;

import code.DoubleListToQueenAndStack;

//双向链表，其中包含头头部添加，尾部添加，头部弹出，尾部弹出四个方法
public  class DoubleEndList<T> {
    public DoubleListToQueenAndStack.Node<T> head;
    public DoubleListToQueenAndStack.Node<T> tail;

    public void addFromHead(T value) {
        DoubleListToQueenAndStack.Node<T> cur = new DoubleListToQueenAndStack.Node<>(value);
        if (head == null) {
            tail = cur;
            head = cur;
        } else {
            cur.next = head;
            head.last = cur;
            head = cur;
        }
    }

    public void addFromBottom(T value) {
        DoubleListToQueenAndStack.Node<T> cur = new DoubleListToQueenAndStack.Node<>(value);
        if (head == null) {
            tail = cur;
            head = cur;
        } else {
            tail.next = cur;
            cur.last = tail;
            tail = cur;

        }

    }

    public T popFromHead() {
        if (head == null) {
            return null;
        }
        DoubleListToQueenAndStack.Node<T> cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = cur.next;
            cur.next = null;
            head.last = null;
        }
        return cur.data;//返回节点的值，也就是封装的泛数据类型T,可以是任何一个对象。
    }

    public T popFromBottom() {
        if (head == null) {
            return null;
        }
        DoubleListToQueenAndStack.Node<T> cur = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            tail.next = null;
            cur.last = null;
        }
        return cur.data;

    }
}