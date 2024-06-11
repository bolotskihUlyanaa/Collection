package ulyana;

import java.util.*;

public class MyLinkedList<T extends Comparable<T>> {
    private MyNode first;
    private MyNode last;
    private int size;

    public class MyNode {
        T value;
        MyNode prev;
        MyNode next;

        public MyNode(T value, MyNode prev, MyNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(MyNode prev){
            this.prev = prev;
        }

        public void setNext(MyNode next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public MyNode getPrev(){
            return prev;
        }

        public MyNode getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }

    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public MyLinkedList(Collection<? extends T> collection) {
        super();
        addAll(collection);
    }

    //когда размер 0
    public boolean init(T value) {
        MyNode newNode = new MyNode(value, null, null);
        first = newNode;
        last = newNode;
        size++;
        return true;
    }

    public boolean add(T value) {
        if (size == 0) return init(value);
        MyNode newNode = new MyNode(value, last, null);
        last.setNext(newNode);
        last = newNode;
        size++;
        return true;
    }

    public boolean addFirst(T value) {
        if (size == 0) return init(value);
        MyNode newNode = new MyNode(value, null, first);
        first.setPrev(newNode);
        first = newNode;
        size++;
        return true;
    }

    public boolean add(int index, T value) {
        if (index == size) return add(value);
        if (index == 0) return addFirst(value);
        MyNode node = search(index);
        MyNode newNode = new MyNode(value, node.getPrev(), node);
        node.getPrev().setNext(newNode);
        node.setPrev(newNode);
        size++;
        return true;
    }

    public T get(int index) {
        return search(index).getValue();
    }

    public int size(){
        return size;
    }

    public void addAll(Collection<? extends T> collection) {
        for(T i:collection) {
            add(i);
        }
    }

    public MyNode search(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        MyNode node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public boolean remove() {
        MyNode node = first.getNext();
        node.setPrev(null);
        first = node;
        size--;
        return true;
    }

    public boolean remove(int index) {
        if (index == 0) return remove();
        if (index == size) return removeLast();
        MyNode node = search(index);
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        return true;
    }

    public boolean removeLast() {
        last.getPrev().setPrev(null);
        size--;
        return true;
    }

    public void bubbleSort() {
        for (int j = 0; j < size - 1; j++) {
            boolean f = false;
            MyNode node = first;
            for (int i = 0; i < size - 1; i++) {
                T a = node.getValue();
                T b = node.getNext().getValue();
                if (a.compareTo(b) > 0) {
                    f = true;
                    break;
                }
                node = node.getNext();
            }
            if (f) {
                node = first;
                for (int i = 0; i < size - 1; i++) {
                    T a = node.getValue();
                    T b = node.getNext().getValue();
                    if (a.compareTo(b) > 0) {
                        node.setValue(b);
                        node.getNext().setValue(a);
                    }
                    node = node.getNext();
                }
            } else {
                break;
            }
        }
    }

    static public MyLinkedList sort(Collection<? extends Comparable> collection) {
        MyLinkedList l = new MyLinkedList();
        l.addAll(collection);
        l.bubbleSort();
        return l;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        MyNode node = first;
        for (int i = 0; i < size; i++) {
            str.append(node.getValue());
            str.append(' ');
            node = node.getNext();
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(MyLinkedList.sort(Arrays.asList("e", "d", "c", "b", "a")));
    }
}