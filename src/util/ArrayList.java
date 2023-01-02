package util;

public class ArrayList<E> implements List<E>{
    //The field responsible for the size of the dynamic array be default
    private static final int DEFAULT_CAPACITY = 10;

    //The field that stores all elements of the collection
    transient Object[] elementData;

    //A counter field that stores the number of elements actually in the array
    private int size;

    //
    private static final Object[] EMPTY_ELEMENTDATA = {};

    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    //Constructs an empty list with the specified initial capacity.
    //Parameter: initialCapacity - the initial capacity of the list from 0 to java.lang.Integer.MAX_VALUE
    //Throws: IllegalArgumentException – if the specified initial capacity is negative
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }

    }

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
