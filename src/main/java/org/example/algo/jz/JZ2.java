package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca">JZ2</a>
 * <p>
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 * 数据范围：0≤n≤1000
 * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
 */
public class JZ2 {

    public static void main(String[] args) {
        JZ2 jz2 = new JZ2();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode head = ModelParser.buildList(inOut[0]);
            String expect = inOut[1];
            ListNode actual = jz2.ReverseList(head);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz1", TargetType.JZ);
    }

    public ListNode ReverseList(ListNode head) {
        return f3(head);
    }

    /**
     * 头插法，双链表解决
     */
    public ListNode f3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 栈解决
     */
    public ListNode f2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Deque<Integer> valStk = new ArrayDeque<>();
        ListNode cursor = head;
        while (cursor != null) {
            valStk.push(cursor.val);
            cursor = cursor.next;
        }

        ListNode dummy = new ListNode(0);
        cursor = dummy;
        while (!valStk.isEmpty()) {
            cursor = cursor.next = new ListNode(valStk.pop());
        }
        return dummy.next;
    }

    /**
     * 递归解决
     */
    public ListNode f1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return recur(head, null);
    }

    ListNode recur(ListNode curr, ListNode prev) {
        if (curr == null) {
            return prev;
        }

        ListNode newHead = recur(curr.next, curr);
        // 当前逻辑，改动指针
        curr.next = prev;
        return newHead;
    }
}
