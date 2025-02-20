package com.unal.una_huella.UNaHuellaLauncher.ED;

import com.unal.una_huella.UNaHuellaLauncher.Entities.*;

import java.util.Iterator;

public class DoubleLinkedList<T> implements List<T> {

    private long size;
    private NodoList<T> head;
    private NodoList<T> tail;

    public DoubleLinkedList() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (head == tail && size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void pushFront(T key) {
        NodoList<T> newNode = new NodoList<T>(key, head, null);
        newNode.next.prev = newNode;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public void pushBack(T key) {
        NodoList<T> newNode = new NodoList<T>(key, null, null);
        if (tail == null) {
            head = tail = newNode;
            newNode.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T topFront() {
        if (head != null) {
            return head.key;
        } else {
            return null;
        }
    }

    @Override
    public T popFront() {
        if (head == null) {
            System.out.println("( ._.)/");
            return null;
        }
        NodoList<T> temp = head;
        if (head != tail) {
            head.next.prev = null;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        if (temp != null) {
            return temp.key;
        } else {
            return null;
        }
    }

    @Override
    public T topBack() {
        if (tail != null) {
            return tail.key;
        } else {
            return null;
        }
    }

    @Override
    public T popBack() {
        if (head == null) {
            System.out.println("_/(._. )");
            return null;
        }
        NodoList<T> temp = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        if (temp != null) {
            return temp.key;
        } else {
            return null;
        }
    }

    @Override
    public boolean find(T key) {
        NodoList<T> temp = head;
        long i = 0;
        while (i < size) {
            if (temp.key == key) {
                return true;
            }
            temp = temp.next;
            i++;
        }
        return false;
    }

    public NodoList findNode(T key) {
        NodoList<T> temp = head;
        long i = 0;
        while (i < size) {
            if (temp.key == key) {
                return temp;
            }
            temp = temp.next;
            i++;
        }
        return null;
    }

    public T findById(String id) {
        long i = 0;
        if (head != null && head.key instanceof Usuario) {
            NodoList<Usuario> temp = (NodoList<Usuario>) head;
            while (i < this.size) {
                if (temp.key.getId_usuario().equals(id)) {
                    return (T) temp.key;
                }
                temp = temp.next;
                i++;
            }
        } else if (head != null && head.key instanceof Mascota) {
            long mascotaId = Long.parseLong(id);
            NodoList<Mascota> temp = (NodoList<Mascota>) head;
            while (i < this.size) {
                if (temp.key.getId_mascota() == mascotaId) {
                    return (T) temp.key;
                }
                temp = temp.next;
                i++;
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public void delete(T key) {
        NodoList temp = findNode(key);
        if (temp != null) {
            if (head == tail) {
                popBack();
            } else {
                if (temp == head) {
                    temp.next.prev = temp.prev;
                    head = temp.next;
                    temp.next = null;
                } else if (temp == tail) {
                    temp.prev.next = temp.next;
                    tail = temp.prev;
                    temp.prev = null;
                } else {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                    temp.next = null;
                }
                size--;
            }
        }

    }

    public void update(T key){
        NodoList temp = findNode(key);
        if (temp.key instanceof Mascota){
            temp.key = key;
        }
        else{
            System.out.println("\n\n\tno se pudo actualizar el elemento en la linkedList\n");
            return;
        }
    }

    @Override
    public void addBefore(NodoList node, T key) {
        NodoList<T> newNode = new NodoList<T>(key, node, node.prev);
        node.prev = newNode;
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }
        if (head == node) {
            head = newNode;
        }
    }

    @Override
    public void addAfter(NodoList node, T key) {
        NodoList<T> newNode = new NodoList<T>(key, node.next, node);
        node.next = newNode;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        if (tail == node) {
            tail = newNode;
        }
    }

    public Iterable<T> iteratorToIterable(Iterator<T> iterator) {
        return () -> iterator;
    }

    public Iterable<T> iterable() {
        Iterator<T> it = iterator();
        Iterable<T> to = iteratorToIterable(it);

        return to;
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new ListIterator<T>(this);
        return it;
    }

    public NodoList<T> getHead() {
        return head;
    }

    public NodoList<T> getTail() {
        return tail;
    }

}
