package util;

public interface MyList <E> {
    boolean add(E e);
    //void add(int index, E element);
    E set(int index, E element);
    E get(int index);
    E remove(int index);
    int size();
}
