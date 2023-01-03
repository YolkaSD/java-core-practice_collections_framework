package util;

public class MyArray {
    public static Object[] copyOf(Object[] oldElementData, int newLength) {
        Object[] copy = new Object[newLength];
        for (int i = 0; i < oldElementData.length; i++)
            copy[i] = oldElementData[i];
        return copy;
    }
    public static Object[] copyOffset(Object[] oldElementData, int index) {
        int length = oldElementData.length;
        Object[] copy = new Object[length];
        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == index)
                j--;
            else
                copy[i] = oldElementData[j];
        }
        return copy;
    }

}
