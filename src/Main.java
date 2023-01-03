
import util.MyArrayList;
import util.MyList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyList<String> stringList = new MyArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("7");
        stringList.add("7");
        stringList.add("7");
        stringList.add("7");

        List<String> stringList1 = new ArrayList<>(10);
        stringList1.add("1");
        stringList1.add("2");



        System.out.println(stringList);
        System.out.println(stringList1);
    }
}