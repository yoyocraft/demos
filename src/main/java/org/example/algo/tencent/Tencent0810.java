package org.example.algo.tencent;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 搜索提示，前三个，字典序
 */
public class Tencent0810 {

    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        /*
          [
           [mobile, moneypot, monitor], // m
           [mobile, moneypot, monitor], // mo
           [mouse, mousepad], // mou
           [mouse, mousepad], // mous
           [mouse, mousepad] // mouse
          ]
         */
        solve(products, searchWord);
    }

    static void solve(String[] products, String searchWord) {
        Arrays.sort(products);

        Trie root = new Trie();
        for (String s : products) {
            root.insert(s);
        }

        List<List<String>> ans = new ArrayList<>();

        char[] ss = searchWord.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : ss) {
            builder.append(c);
            List<String> nodes = root.searchPrefix(builder.toString());
            ans.add(nodes);
        }

        System.out.println(ans);
    }

    static class Trie {

        boolean isEnd;
        Trie[] children;
        String s;

        public Trie() {
            this.isEnd = false;
            this.children = new Trie[26];
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
            node.s = word;
        }

        public List<String> searchPrefix(String prefix) {
            Trie node = this;
            List<String> nodes = new ArrayList<>();
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    return null;
                }
                node = node.children[idx];
            }
            // node.children
            f(node, nodes);
            return nodes;
        }

        public void f(Trie node, List<String> nodes) {
            if (node == null) {
                return;
            }
            if (nodes.size() >= 3) {
                return;
            }
            if (node.isEnd) {
                nodes.add(node.s);
            }
            for (Trie trie : node.children) {
                if (trie == null) {
                    continue;
                }
                f(trie, nodes);
            }
        }
    }
}