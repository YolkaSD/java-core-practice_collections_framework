package util;

public class MyLinkedList<E> implements MyList<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    @Override
    public boolean add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldElem = x.item;
        x.item = element;
        return oldElem;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;

    }

    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> removeNode = node(index);
        Node<E> prev = removeNode.prev;
        Node<E> next = removeNode.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            removeNode.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            removeNode.next = null;
        }
        size--;
        return removeNode.item;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        E item;

        private Node<E> prev;

        private Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}

