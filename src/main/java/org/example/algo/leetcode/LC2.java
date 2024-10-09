package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

/**
 * @author yoyocraft
 * @date 2024/10/09
 */
public class LC2 {

    public static void main(String[] args) {
        LC2 lc2 = new LC2();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode l1 = ModelParser.buildList(inOut[0]);
            ListNode l2 = ModelParser.buildList(inOut[1]);
            String expect = inOut[2];
            ListNode actual = lc2.addTwoNumbers(l1, l2);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "lc2");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;

            int s = n1 + n2 + carry;
            carry = s / 10;
            s %= 10;

            curr = curr.next = new ListNode(s);

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
