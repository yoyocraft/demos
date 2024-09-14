package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

/**
 * @author yoyocraft
 * @date 2024/09/14
 */
public class LC24 {

    public static void main(String[] args) {
        LC24 lc24 = new LC24();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode head = ModelParser.buildList(inOut[0]);
            String expect = inOut[1];
            ListNode actual = lc24.swapPairs(head);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc24");
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nxt = head.next;
        head.next = swapPairs(nxt.next);
        nxt.next = head;
        return nxt;
    }
}
