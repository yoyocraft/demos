package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

/**
 * @author yoyocraft
 * @date 2024/09/08
 */
public class LC25 {
    public static void main(String[] args) {
        LC25 lc25 = new LC25();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode head = ModelParser.buildList(inOut[0]);
            int k = Integer.parseInt(inOut[1]);
            String expect = inOut[2];
            ListNode actual = lc25.reverseKGroup(head, k);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc25");
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tmp = head;
        int len = 0;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }

        ListNode dummy = new ListNode(0, head);
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
}
