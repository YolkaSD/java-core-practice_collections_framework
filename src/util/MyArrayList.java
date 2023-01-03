package util;

import java.util.Arrays;



public class MyArrayList<E> implements MyList<E>{
    //The field responsible for the size of the dynamic array be default
    private static final int DEFAULT_CAPACITY = 10;

    //The field that stores all elements of the collection
    transient Object[] elementData;

    //A counter field that stores the number of elements actually in the array
    private int size;

    //
    private static final Object[] EMPTY_ELEMENTDATA = {};

    public MyArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    //Constructs an empty list with the specified initial capacity.
    //Parameter: initialCapacity - the initial capacity of the list from 0 to java.lang.Integer.MAX_VALUE
    //Throws: IllegalArgumentException – if the specified initial capacity is negative
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }

    }

    public void checkOf(){
        System.out.println("elementData: " + Arrays.toString(elementData));
        System.out.println("elementData.length: " + elementData.length);
        System.out.println("size: " + size);
    }

    @Override
    public boolean add(E element) {
        if (size == elementData.length)
            elementData = grow();
        elementData[size] = element;
        size++;
        return true;
    }
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (size == elementData.length)
            elementData = grow();
        elementData = MyArray.copyOffset(elementData, index);
        elementData[index] = element;
        size++;
    }

    private Object[] grow() {
        if (elementData.length > 0 || elementData != EMPTY_ELEMENTDATA) {
            int newCapacity = elementData.length + (elementData.length >> 1) + 1;
            return elementData = MyArray.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[DEFAULT_CAPACITY];
        }
    }


    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E oldValue = elementData(index);
        elementData[index] = element;
        checkOf();
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return elementData(index);
    }
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E oldValue = elementData(index);
        elementData = MyArray.copyNegativeOffset(elementData, index);
        checkOf();
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }
}
