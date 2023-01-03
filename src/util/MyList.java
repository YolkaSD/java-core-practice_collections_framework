package util;

public interface MyList <E> {
    boolean add(E e);
    E set(int index, E element);
    E get(int index);
    boolean remove(Object o);
    int size();
}
