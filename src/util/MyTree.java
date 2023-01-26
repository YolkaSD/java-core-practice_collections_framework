package util;

public interface MyTree <E extends Comparable<E>>{
    boolean add(E e);
    boolean remove(E e);
    void printTree();
}
