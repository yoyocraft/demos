package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
public class LC146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); // {1:1}
        cache.put(2, 2); // {1:1, 2:2}
        int v1 = cache.get(1); // return 1
        OjAssertUtil.assertEquals(1, v1);
        cache.put(3, 3); // {3:3, 1:1}
        int v2 = cache.get(2); // return -1
        OjAssertUtil.assertEquals(-1, v2);
        cache.put(4, 4); // {4:4, 3:3}
        int v3 = cache.get(1); // return -1
        OjAssertUtil.assertEquals(-1, v3);
        int v4 = cache.get(3); // return 3
        OjAssertUtil.assertEquals(3, v4);
        int v5 = cache.get(4); // return 4
        OjAssertUtil.assertEquals(4, v5);
    }

    static class LRUCache {

        int capacity;
        Node dummy;
        Map<Integer, Node> keyToNodeCache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.keyToNodeCache = new HashMap<>(capacity);
            this.dummy = new Node();
            dummy.next = dummy;
            dummy.prev = dummy;
        }

        public int get(int key) {
            Node node = getNode(key);
            return node != null ? node.value : -1;
        }

        public void put(int key, int value) {
            Node node = getNode(key);
            if (node != null) {
                node.value = value;
                return;
            }

            node = new Node(key, value);
            keyToNodeCache.put(key, node);
            pushFront(node);
            if (keyToNodeCache.size() > capacity) {
                Node backNode = dummy.prev;
                keyToNodeCache.remove(backNode.key);
                remove(backNode);
            }
        }

        Node getNode(int key) {
            if (!keyToNodeCache.containsKey(key)) {
                return null;
            }

            Node node = keyToNodeCache.get(key);
            remove(node);
            pushFront(node);
            return node;
        }

        void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        void pushFront(Node node) {
            node.prev = dummy;
            node.next = dummy.next;
            node.prev.next = node;
            node.next.prev = node;
        }

        static class Node {
            int key, value;
            Node prev, next;

            Node() {
            }

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
