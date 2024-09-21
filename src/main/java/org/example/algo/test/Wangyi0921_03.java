package org.example.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/09/21
 */
public class Wangyi0921_03 {
    private static final String NULL_VALUE = "-1";
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = in.readLine();
        String[] inOut = line.split(SPACE);
        String headAddr = inOut[0];
        int N = Integer.parseInt(inOut[1]);
        int K = Integer.parseInt(inOut[2]);
        Map<String /* curAddr */, String /* nextAddr */> addrMapping = new HashMap<>();
        Map<String /* addr */, String /* value */> valMapping = new HashMap<>();
        Map<String /* value */, String /* addr */> valAddrMapping = new HashMap<>();
        for (int i = 0; i < N; i++) {
            line = in.readLine();
            inOut = line.split(SPACE);
            String curAddr = inOut[0];
            String nextAddr = inOut[2];
            String val = inOut[1];
            addrMapping.put(curAddr, nextAddr);
            valMapping.put(curAddr, val);
            valAddrMapping.put(val, curAddr);
        }

        ListNode head = build(headAddr, addrMapping, valMapping);
        ListNode curr = reverseKGroup(head, K, N);
        while (curr != null) {
            String val = curr.val;
            String curAddr = valAddrMapping.get(val);
            ListNode next = curr.next;
            String nextAddr;
            if (next == null) {
                nextAddr = NULL_VALUE;
            } else {
                nextAddr = valAddrMapping.get(next.val);
            }

            out.printf("%s %s %s\n",
                    curAddr,
                    val,
                    nextAddr);

            curr = next;
        }
        out.flush();
        out.close();
        in.close();
    }

    static ListNode build(String headAddr,
                          Map<String /* curAddr */, String /* nextAddr */> addrMapping,
                          Map<String /* addr */, String /* value */> valMapping) {
        ListNode dummy = new ListNode(), curr = dummy;
        String addr = headAddr;
        while (!NULL_VALUE.equals(addr)) {
            ListNode node = new ListNode(valMapping.get(addr));
            curr = curr.next = node;
            addr = addrMapping.get(addr);
        }
        return dummy.next;
    }

    static ListNode reverseKGroup(ListNode head, int k, int N) {
        if (head == null) {
            return null;
        }

        int len = N;

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode preHead = dummy;
        ListNode curr = head, prev = null, next;
        for (; len >= k; len -= k) {
            for (int i = 0; i < k; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            ListNode tail = preHead.next;
            preHead.next = prev;
            tail.next = curr;

            preHead = tail;
            prev = tail;
        }
        return dummy.next;
    }

    static class ListNode {
        String val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(String val) {
            this.val = val;
        }
    }


}
