package javaee;

public class MyLinkListMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V val;
        Node next;

        public Node(K key, V val, Node node) {
            this.key = key;
            this.val = val;
            this.next = node;
        }

        public Node(K key, V val) {
            this(key, val, null);
        }

        public Node() {
            this(null, null, null);
        }
    }

    private Node dummyHead;
    private int size;

    private Node getNode(K key) {
        Node curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.key == key) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    private boolean containKey(K key) {
        Node node = getNode(key);
        return node != null;
    }

    @Override
    public V get(Object key) {
        Node node = dummyHead.next;
        while (node != null) {
            if (node.key == key) {
                return node.val;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            node = new Node(key, value);
            Node curNode = dummyHead.next;
            while(curNode.next!=null){
                curNode = curNode.next;
            }
            curNode.next = node;
        } else {
            node.val = value;
        }
        return node.val;
    }
}
