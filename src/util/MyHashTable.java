package util;

public class MyHashTable<K, V> implements MyMap<K, V>{

    private Node<K, V>[] values = new Node[4];

    private int count = 0;

    @Override
    public void put(K key, V value) {
        put2(key, value, values);
        if (count >= values.length * 0.75) {
            overrideValue();
        }
    }

    @Override
    public V get(K key) {
        int hashCode = key != null ? key.hashCode(): 0;
        int index = Math.abs(hashCode % values.length);
        Node<K, V> currentNode = values[index];
        while (currentNode != null) {
            if (currentNode.hashCode == hashCode &&
                    (key == null ? currentNode.key == null : currentNode.key.equals(key))) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hashCode = key != null ? key.hashCode(): 0;
        int index = Math.abs(hashCode % values.length);
        Node<K, V> parentNode = null;
        Node<K, V> currentNode =  values[index];
        while (currentNode != null) {
            if (currentNode.hashCode == hashCode &&
                    (key == null ? currentNode.key == null : currentNode.key.equals(key))) {
                if (parentNode != null) {
                    parentNode.next = currentNode.next;
                } else {
                    values[index] = currentNode.next;
                }
                V oldValue = currentNode.value;
                currentNode.value = null;
                count--;
                return oldValue;
            }
            parentNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public String toString(){
        int max = count - 1;
        if (max == -1)
            return "{}";

        StringBuilder sb = new StringBuilder();

        sb.append("{");
        int i = -1;
        for (Node<K, V> node : values) {
            if (node != null) {
                sb.append(node.key);
                sb.append("=");
                sb.append(node.value);
                i++;
                if (i != max) {
                    sb.append(", ");
                }
                if (node.next != null) {

                    while (node.next != null) {
                        node = node.next;
                        sb.append(node.key);
                        sb.append("=");
                        sb.append(node.value);
                        i++;
                        if (i != max) {
                            sb.append(", ");
                        }
                    }
                }
            }

        }
        sb.append("}");
        return sb.toString();
    }

    private void overrideValue() {
        Node<K, V> currentNode;
        Node<K, V>[] newValues = new Node[values.length * 2];
        for (Node<K, V> value : values) {
            currentNode = value;
            while (currentNode != null) {
                put2(currentNode.key, currentNode.value, newValues);
                currentNode = currentNode.next;
            }
        }
        values = newValues;
    }

    private void put2(K key, V value, Node<K, V>[] values) {
        int hashCode = key != null ? key.hashCode() : 0;
        Node<K, V> newNode = new Node<>(key, hashCode);
        newNode.value = value;
        int index = Math.abs(newNode.hashCode % values.length);
        if (values[index] == null) {
            values[index] = newNode;
        } else {
            Node<K, V> parentNode = values[index];
            while (parentNode != null){
                if (parentNode.hashCode == hashCode &&
                        (key == null ? parentNode.key == null : parentNode.key.equals(key))) {
                    parentNode.value = value;
                    count--;
                    break;
                }
                if (parentNode.next == null) {
                    parentNode.next = newNode;
                    break;
                }
                parentNode = parentNode.next;
            }
        }
        count++;
    }

    private static class Node<K, V> {

        private final K key;

        private V value;

        private final int hashCode;

        private Node<K, V> next;

        public Node( K key, int hashCode) {
            this.key = key;
            this.hashCode = hashCode;
        }

    }
}
