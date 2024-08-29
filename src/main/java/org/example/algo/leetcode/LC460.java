package org.example.algo.leetcode;

import org.example.algo.OjAssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
public class LC460 {

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1); // cache=[1,_], cnt(1)=1
        lfu.put(2, 2); // cache=[2,1], cnt(2)=1, cnt(1)=1
        int v1 = lfu.get(1); // 返回 1, cache=[1,2], cnt(2)=1, cnt(1)=2
        OjAssertUtil.assertEquals(1, v1);
        lfu.put(3, 3); // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小，cache=[3,1], cnt(3)=1, cnt(1)=2
        int v2 = lfu.get(2); // 返回 -1（未找到）
        OjAssertUtil.assertEquals(-1, v2);
        int v3 = lfu.get(3); // 返回 3，cache=[3,1], cnt(3)=2, cnt(1)=2
        OjAssertUtil.assertEquals(3, v3);
        lfu.put(4, 4); // 去除键 1，1 和 3 的 cnt 相同，但 1 最久未使用，cache=[4,3], cnt(4)=1, cnt(3)=2
        int v4 = lfu.get(1); // 返回 -1（未找到）
        OjAssertUtil.assertEquals(-1, v4);
        int v5 = lfu.get(3); // 返回 3，cache=[3,4], cnt(4)=1, cnt(3)=3
        OjAssertUtil.assertEquals(3, v5);
        int v6 = lfu.get(4); // 返回 4，cache=[3,4], cnt(4)=2, cnt(3)=3
        OjAssertUtil.assertEquals(4, v6);
    }

    static class LFUCache {

        private final int capacity;
        private final Map<Integer, Node> keyToNodeCache;
        private final Map<Integer, Node> freqToDummyCache;
        private int minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.keyToNodeCache = new HashMap<>(capacity);
            this.freqToDummyCache = new HashMap<>();
            this.minFreq = 0;
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

            if (keyToNodeCache.size() == capacity) {
                Node dummy = freqToDummyCache.get(minFreq);
                Node backNode = dummy.prev;
                keyToNodeCache.remove(backNode.key);
                remove(backNode);
                if (dummy.prev == dummy) {
                    freqToDummyCache.remove(minFreq);
                }
            }

            node = new Node(key, value);
            keyToNodeCache.put(key, node);
            pushFront(1, node);
            minFreq = 1;
        }

        Node getNode(int key) {
            if (!keyToNodeCache.containsKey(key)) {
                return null;
            }

            Node node = keyToNodeCache.get(key);
            remove(node);
            Node dummy = freqToDummyCache.get(node.freq);
            if (dummy.prev == dummy) {
                freqToDummyCache.remove(node.freq);
                if (minFreq == node.freq) {
                    minFreq++;
                }
            }
            pushFront(++node.freq, node);
            return node;
        }

        void pushFront(int freq, Node node) {
            Node dummy = freqToDummyCache.computeIfAbsent(freq, k -> newList());
            node.prev = dummy;
            node.next = dummy.next;
            node.prev.next = node;
            node.next.prev = node;
        }

        Node newList() {
            Node dummy = new Node(0, 0);
            dummy.next = dummy;
            dummy.prev = dummy;
            return dummy;
        }

        void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }


        static class Node {
            int key, value, freq;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.freq = 1;
            }
        }
    }
}
