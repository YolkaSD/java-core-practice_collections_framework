
import util.MyArrayList;
import util.MyList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> integerMyList = new MyArrayList<>();
        integerMyList.add(1);
        integerMyList.add(2);
        integerMyList.add(3);

        int size = integerMyList.size();
        if (size == 3) System.out.println("Test passed");
        else System.out.println("Test filed");

        if (integerMyList.get(1) == 2) System.out.println("Test passed");
        else System.out.println("Test filed");

        integerMyList.remove(1); // remove value 2
        size = integerMyList.size();
        if (size == 2) System.out.println("Test passed");
        else System.out.println("Test filed");

        if (integerMyList.get(1) == 3) System.out.println("Test passed");
        else System.out.println("Test filed");

        integerMyList.add(1, 2); // add value 2 on index
        if (integerMyList.get(1) == 2) System.out.println("Test passed");
        else System.out.println("Test filed");

        size = integerMyList.size();
        if (size == 3) System.out.println("Test passed");
        else System.out.println("Test filed");

        integerMyList.set(0, 5);
        if (integerMyList.get(0) == 5) System.out.println("Test passed");
        else System.out.println("Test filed");

        size = integerMyList.size();
        if (size == 3) System.out.println("Test passed");
        else System.out.println("Test filed");


    }
}