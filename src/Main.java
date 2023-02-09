import util.MyHashTable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer, String> myHashTable = new MyHashTable<>();
        //Вывести пустую таблицу
        if (myHashTable.toString().equals("{}")){
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Получить несуществующий элемент
        if (myHashTable.get(1212) == null) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести элемент "яблоко" с ключом 0
        myHashTable.put(0, "Яблоко");
        if (myHashTable.get(0).equals("Яблоко")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести новый элемент "Яблоко Австрийское" с ключом 0
        myHashTable.put(0, "Яблоко Австрийское");
        if (myHashTable.get(0).equals("Яблоко Австрийское")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести элемент "Яблоко" с ключом 1
        myHashTable.put(1, "Яблоко");
        if (myHashTable.get(1).equals("Яблоко")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести элемент "Апельсин" с ключом null
        myHashTable.put(null, "Апельсин");
        if (myHashTable.get(null).equals("Апельсин")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести новый элемент "Апельсин Африканский" с ключом null
        myHashTable.put(null, "Апельсин Африканский");
        if (myHashTable.get(null).equals("Апельсин Африканский")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести новый элемент "null" с ключом 2
        myHashTable.put(2, null);
        if (myHashTable.get(2) == null) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Внести новый элемент "null" с ключом 3
        myHashTable.put(3, null);
        if (myHashTable.get(3) == null) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Удалить элемент под ключом null
        if (myHashTable.remove(null).equals("Апельсин Африканский") && myHashTable.get(null) == null) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }

        //Удалить элемент под ключом 1
        if (myHashTable.remove(1).equals("Яблоко") && myHashTable.get(null) == null) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }


        System.out.println(myHashTable);

    }
}