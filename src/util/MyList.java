package util;

public interface MyList <E> {
    void add(E e);
    void add(int index, E e);
    E set(int index, E element);
    E get(int index);
    boolean remove(Object o);
    int size();
}
