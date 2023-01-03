package util;

public class MyArray {
    public static Object[] copyOf(Object[] oldElementData, int newLength) {
        Object[] copy = new Object[newLength];
        for (int i = 0; i < oldElementData.length; i++)
            copy[i] = oldElementData[i];
        return copy;
    }
}
