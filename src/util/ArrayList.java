package util;

public class ArrayList<E> implements List<E>{

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
