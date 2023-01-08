import util.MyLinkedList;
import util.MyList;


public class Main {
    public static void main(String[] args) {
        MyList<Integer> integerMyList = new MyLinkedList<>();
        integerMyList.add(1);
        integerMyList.add(2);
        integerMyList.add(3);

        int size = integerMyList.size();
        if (size == 3) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        if (integerMyList.get(1) == 2) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        int j = integerMyList.remove(1); // remove value 2
        size = integerMyList.size();
        if (size == 2) {
            System.out.println("Test passed");
        }
        else {
            System.out.println("Test failed");
        }
        if (j == 2) {System.out.println("Test passed");
        }
        else {
            System.out.println("Test failed");
        }

        if (integerMyList.get(1) == 3) {
            System.out.println("Test passed");
        }
        else {
        System.out.println("Test failed");
    }

        try {
            integerMyList.get(-1);
            System.out.println("Test failed");
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Test passed");
        }

        try {
            integerMyList.get(120);
            System.out.println("Test failed");
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Test passed");
        }

    }
}