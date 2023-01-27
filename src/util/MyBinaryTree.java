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
        Node<E> parentNode = currentNode;
        while (true) {
            if (e.compareTo(currentNode.value) == 0) {
                if (currentNode.rightChild != null && currentNode.leftChild != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.rightChild;
                    if (currentNode.leftChild == null) {
                        parentNode.value = currentNode.value;
                        if (currentNode.rightChild != null) {
                            parentNode.rightChild = currentNode.rightChild;
                        } else {
                            parentNode.rightChild = null;
                        }
                    } else {
                        Node<E> prefCurrentNode = currentNode;
                        currentNode = currentNode.leftChild;
                        while (currentNode.leftChild != null) {
                            prefCurrentNode = currentNode;
                            currentNode = currentNode.leftChild;
                        }
                        if (currentNode.rightChild != null) {
                            parentNode.value = currentNode.value;
                            prefCurrentNode.leftChild = currentNode.rightChild;
                            currentNode.rightChild = null;
                        } else {
                            parentNode.value = currentNode.value;
                            if (currentNode.value.compareTo(e) > 0) {
                                prefCurrentNode.leftChild = null;
                            } else {
                                prefCurrentNode.rightChild = null;
                            }

                        }
                    }
                } else if (currentNode.rightChild != null) {
                    if (parentNode != root) {
                        parentNode.leftChild = currentNode.rightChild;
                    } else {
                        if (parentNode.value.compareTo(e) < 0) {
                            parentNode = parentNode.rightChild;
                        }
                        parentNode.value = parentNode.rightChild.value;
                        parentNode.rightChild = null;
                    }
                } else if (currentNode.leftChild != null) {
                    if (parentNode.value.compareTo(e) < 0 ) {
                        parentNode.rightChild = currentNode.leftChild;
                    } else {
                        if (parentNode == currentNode) {
                            parentNode.value = parentNode.leftChild.value;
                            parentNode.leftChild = null;
                        } else {
                            parentNode.leftChild = currentNode.leftChild;
                        }
                    }
                } else {
                    if (currentNode.value.compareTo(e) > 0 ) {
                        parentNode.rightChild = null;
                    } else {
                        parentNode.leftChild = null;
                    }
                    if (root.leftChild == null && root.rightChild == null) {
                        root = null;
                    }
                }
                return true;
            } else if (e.compareTo(currentNode.value) < 0) {
                if (currentNode.leftChild == null) {
                    return false;
                }
                parentNode = currentNode;
                currentNode = parentNode.leftChild;
            } else {
                if (currentNode.rightChild == null) {
                    return false;
                }
                parentNode = currentNode;
                currentNode = parentNode.rightChild;
            }
        }
    }


    @Override
    public void printTree() {
        printTreeWidth();
        printTreeDeep();
        //printTreeTest();
    }

    private void printTreeWidth(){
        Queue<Node<E>> stack = new LinkedList<>();
        stack.add(root);
        StringBuilder stringBuilder;
        if (root != null) {
            stringBuilder = new StringBuilder("printTreeWidth:  ");
        } else {
            return;
        }
        while(!stack.isEmpty()) {
            Node<E> node = stack.poll();
            stringBuilder.append(node).append(" ");
            if(node.leftChild != null && node.rightChild != null) {
                stack.add(node.leftChild);
                stack.add(node.rightChild);
            } else if(node.leftChild != null) {
                stack.add(node.leftChild);
            } else if(node.rightChild != null) {
                stack.add(node.rightChild);
            }
        }
        System.out.println(stringBuilder);
    }

    private void printTreeDeep(){
        Stack<Node<E>> stack = new Stack<>();
        stack.add(root);
        StringBuilder stringBuilder;
        if (root != null) {
            stringBuilder = new StringBuilder("printTreeDeep:  ");
        } else {
            return;
        }
        while(!stack.isEmpty()) {
            Node<E> node = stack.pop();
            stringBuilder.append(node).append(" ");
            if (node.rightChild != null) {
                stack.push(node.rightChild);
            }
            if (node.leftChild != null) {
                stack.push(node.leftChild);
            }
        }
        System.out.println(stringBuilder);
    }

    //javarush method
    public void printTreeTest() { // метод для вывода дерева в консоль
        Stack<Node<E>> globalStack = new Stack<>(); // общий стек для значений дерева
        globalStack.push(root);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty()) { // покуда в общем стеке есть элементы
                Node<E> temp = globalStack.pop(); // берем следующий, при этом удаляя его из стека
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
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }

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
