package org.example.algo.leetcode;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

/**
 * @author yoyocraft
 * @date 2024/09/16
 */
public class LC148 {

    public static void main(String[] args) {
        LC148 lc148 = new LC148();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode head = ModelParser.buildList(inOut[0]);
            ListNode actual = lc148.sortList(head);
            OjAssertUtil.assertEquals(inOut[1], ModelParser.parseString(actual));
        }, "lc148");
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 快指针必须先走一步
        // 反例：假设只有两个节点的情况，找到的中间节点始终是第二个，导致没有切开
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        slow.next = null;

        ListNode leftNode = sortList(head);
        ListNode rightNode = sortList(right);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (leftNode != null || rightNode != null) {
            if (leftNode == null) {
                curr.next = rightNode;
                break;
            }

            if (rightNode == null) {
                curr.next = leftNode;
                break;
            }

            if (leftNode.val < rightNode.val) {
                curr.next = leftNode;
                leftNode = leftNode.next;
            } else {
                curr.next = rightNode;
                rightNode = rightNode.next;
            }

            curr = curr.next;
        }
        return dummy.next;
    }
}
