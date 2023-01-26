package util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyBinaryTree<E extends Comparable<E>> implements MyTree<E> {
    private Node<E> root;

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        if (root == null) {
            root = newNode;
        } else {
            Node<E> currentNode = root;
            Node<E> parentNode;
            while (currentNode != null) {
                parentNode = currentNode;
                if (newNode.value.compareTo(currentNode.value) < 0) {
                    currentNode = parentNode.leftChild;
                    if (currentNode == null) {
                        parentNode.leftChild = newNode;
                    }
                } else if (newNode.value.compareTo(currentNode.value) > 0){
                    currentNode = parentNode.rightChild;
                    if (currentNode == null) {
                        parentNode.rightChild = newNode;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(E e) {
        Node<E> currentNode = root;
        Node<E> parentNode;
        Node<E> prefNode = null;
        while (true) {
            parentNode = currentNode;
            Node<E> findNode = currentNode;

            if (e.compareTo(currentNode.value) == 0) {
                System.out.println("Удаляемый элемент найден: " + currentNode.value + " ");
                if (currentNode.rightChild != null && currentNode.leftChild != null) {
                    System.out.println("У удаляемого элемента два преемника " + currentNode.leftChild + " " + currentNode.rightChild);
                    System.out.print("Переходим к правому преемнику  ");
                    currentNode = parentNode.rightChild;
                    System.out.println(currentNode);
                    System.out.println("Проверяем, есть ли у правого левый, если есть ищем крайний");
                    if (currentNode.leftChild == null) {
                        System.out.println("Левой ветки нет");
                        System.out.println("Преемник " + currentNode.value);
                        System.out.println("Предпоследний перед левым " + parentNode.value);
                        parentNode.rightChild = null;
                        parentNode.value = currentNode.value;
                    } else {
                        while (currentNode.leftChild != null) {
                            parentNode = currentNode;
                            System.out.println("Найденный элемент левый " + currentNode.leftChild.value);
                            currentNode = parentNode.leftChild;
                        }
                        System.out.println("Преемник " + currentNode.value);
                        System.out.println("Предпоследний перед левым " + parentNode.value);
                        if (currentNode.rightChild != null) {
                            parentNode.leftChild = currentNode.rightChild;
                        }
                        if (currentNode.leftChild == null && currentNode.rightChild == null) {
                            parentNode.leftChild = null;
                        }
                    }
                   System.out.println("Перезаписываем значение у удаляемого элемента на найденный");
                   findNode.value = currentNode.value;
                } else if (currentNode.rightChild != null) {
                    currentNode.value = currentNode.rightChild.value;
                    currentNode.rightChild = null;
                } else if (currentNode.leftChild != null) {
                    currentNode.value = currentNode.leftChild.value;
                    parentNode.leftChild = null;
                } else {
                    if (prefNode.leftChild != null) {
                        if (prefNode.leftChild.value.compareTo(currentNode.value) == 0) {
                            prefNode.leftChild = null;
                        } else {
                            prefNode.rightChild = null;
                        }
                    }
                }
                return true;
            } else if (e.compareTo(currentNode.value) < 0) {
                System.out.print("Удаляемый элемент " + e + " меньше чем:  " + currentNode.value + " ");
                prefNode = currentNode;
                if (currentNode.leftChild == null) {
                    return false;
                }
                currentNode = parentNode.leftChild;
            } else {
                System.out.print("Удаляемый элемент " + e + " больше чем:  " + currentNode.value + " ");
                prefNode = currentNode;
                if (currentNode.rightChild == null) {
                    return false;
                }
                currentNode = parentNode.rightChild;
            }
        }
    }


    @Override
    public void printTree() {
        printTreeDeep();
        printTreeTest();
    }

    private void printTreeWidth(){
        Node<E> currentNode = root;
        Node<E> parentNode;
    }

    private void printTreeDeep(){
        Queue<Node<E>> stack = new LinkedList<>();
        stack.add(root);
        StringBuilder stringBuilder = new StringBuilder(root.toString() + " ");
        while(!stack.isEmpty()) {
            Node<E> node = stack.poll();
            if(node.leftChild != null && node.rightChild != null) {
                stack.add(node.leftChild);
                stack.add(node.rightChild);
                stringBuilder.append(node.leftChild).append(" ").append(node.rightChild).append(" ");
            } else if(node.leftChild != null) {
                stack.add(node.leftChild);
                stringBuilder.append(node.leftChild).append(" ");
            } else if(node.rightChild != null) {
                stack.add(node.rightChild);
                stringBuilder.append(node.rightChild).append(" ");
            }
        }
        System.out.println(stringBuilder);
    }
    public void printTreeTest() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(root);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.value); // выводим его значение в консоли
                    localStack.push(temp.leftChild); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }


//    @Override
//    public boolean add(E e) {
//        Node<E> newNode = new Node<>(e);
//        if(root == null) {
//            root = newNode;
//        } else {
//            Node<E> currentNode = root;
//            Node<E> parentNode;
//            while (true) {
//                parentNode = currentNode;
//                if (newNode.value.compareTo(currentNode.value) < 0) {
//                    currentNode = currentNode.leftChild;
//                    if (currentNode == null) {
//                        parentNode.leftChild = newNode;
//                        return true;
//                    }
//                } else {
//                    currentNode = currentNode.rightChild;
//                    if (currentNode == null) {
//                        parentNode.rightChild = newNode;
//                        return true;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean remove(E e) {
//        Node<E> currentNode = root;
//        Node<E> parentNode = root;
//        boolean isLeftChild = true;
//
//        while (currentNode.value != e) { //начинаем поиск узла
//            parentNode = currentNode;
//            if (e.compareTo(currentNode.value) < 0) { // определяем, нужно ли движение влево?
//                isLeftChild = true;
//                currentNode = currentNode.leftChild;
//            }
//            else { // или движение вправо?
//                isLeftChild = false;
//                currentNode = currentNode.rightChild;
//            }
//            if (currentNode == null) {
//                return false; // узел не найден
//            }
//        }
//
//        if(currentNode.leftChild == null && currentNode.rightChild == null) { // если нет потомков - узел просто удаляем
//            if (currentNode == root) { // если узел корень - дерево очищается
//                root = null;
//            } else if(isLeftChild) {
//                parentNode.leftChild = null;
//            } else {
//                parentNode.rightChild = null;
//            }
//        } else if(currentNode.rightChild == null) {
//            if (currentNode == root) {
//                root = currentNode.leftChild;
//            } else if(isLeftChild) {
//                parentNode.leftChild = currentNode.leftChild;
//            } else {
//                parentNode.rightChild = currentNode.leftChild;
//            }
//        } else if(currentNode.leftChild == null) {
//            if (currentNode == root) {
//                root = currentNode.rightChild;
//            } else if(isLeftChild) {
//                parentNode.leftChild = currentNode.rightChild;
//            } else {
//                parentNode.rightChild = currentNode.rightChild;
//            }
//        } else {
//            Node<E> heir = receiveHeir(currentNode);
//            if (currentNode == root) {
//                root = heir;
//            } else if (isLeftChild) {
//                parentNode.leftChild = heir;
//            } else  {
//                parentNode.rightChild = heir;
//            }
//        }
//        return true;
////        if (removeNode != null) {
////            if (removeNode.leftChild == null && removeNode.rightChild == null) {
////                //отсекаем искомый элемент
////            } else if (removeNode.leftChild == null || removeNode.rightChild == null) {
////                //удаляем выбранный узел, на его место ставим потомка
////            } else {
//////                1. Алгоритм поиска приемника (ищем наименьший узел в наборе узлов, больших удаляемого узла)
//////                1. Сперва перейти к правому потомку выбранного узла, значение которого должно быть больше значения узла
//////                2. После перейти к левому потомку правого потомка (если такой существует), дальше - к левому потомку левого потомка и т.д.  следуя вниз по цепи левых потомков
//////                3. Последний левый потом будет являться преемником исходного узла
////            }
////        }
//
//
//    }
//
//    private Node<E> receiveHeir(Node<E> current) {
//        Node<E> parentNode = current;
//        Node<E> heirNode = current;
//        Node<E> currentNode = current.rightChild;
//        while (currentNode != null) {
//            parentNode = heirNode;
//            heirNode = currentNode;
//            currentNode = currentNode.leftChild;
//        }
//
//        if (heirNode != currentNode.rightChild) {
//            parentNode.leftChild = heirNode.rightChild;
//            heirNode.rightChild = currentNode.rightChild;
//        }
//        return heirNode;
//    }
//
//    private Node<E> findNodeByValue(E e){
//        Node<E> currentNode = root;
//        while (currentNode.value != e) {
//            if (e.compareTo(currentNode.value) < 0) {
//                currentNode = currentNode.leftChild;
//            } else if (e.compareTo(currentNode.value) > 0){
//                currentNode = currentNode.rightChild;
//            }
//            if (currentNode == null) {
//                return null;
//            }
//        }
//        return currentNode;
//    }
    private static class Node<E> {
        E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
