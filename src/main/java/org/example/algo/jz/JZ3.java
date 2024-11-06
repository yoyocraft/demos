package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337">JZ3</a>
 * <p>
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 数据范围：0 <= n <= 1000， -1000 <= node.val <= 1000
 * 要求：时间复杂度 O(n)，空间复杂度 O(1)
 */
public class JZ3 {

    public static void main(String[] args) {
        JZ3 jz3 = new JZ3();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode p1 = ModelParser.buildList(inOut[0]);
            ListNode p2 = ModelParser.buildList(inOut[1]);
            String expect = inOut[2];
            ListNode actual = jz3.Merge(p1, p2);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz3", TargetType.JZ);
    }

    public ListNode Merge(ListNode p1, ListNode p2) {
        return f2(p1, p2);
    }

    /**
     * 额外空间
     */
    public ListNode f3(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }

        if (p2 == null) {
            return p1;
        }

        List<Integer> vals = new ArrayList<>();
        ListNode cursor = p1;
        while (cursor != null) {
            vals.add(cursor.val);
            cursor = cursor.next;
        }

        cursor = p2;
        while (cursor != null) {
            vals.add(cursor.val);
            cursor = cursor.next;
        }

        Collections.sort(vals);

        ListNode dummy = new ListNode(0);
        cursor = dummy;
        for (int val : vals) {
            cursor = cursor.next = new ListNode(val);
        }
        return dummy.next;
    }

    /**
     * 递归
     */
    public ListNode f2(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }

        if (p2 == null) {
            return p1;
        }

        if (p1.val < p2.val) {
            p1.next = Merge(p1.next, p2);
            return p1;
        }
        p2.next = Merge(p1, p2.next);
        return p2;
    }

    /**
     * 迭代
     */
    public ListNode f1(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }

        if (p2 == null) {
            return p1;
        }

        ListNode dummy = new ListNode(0), cursor = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cursor.next = p1;
                p1 = p1.next;
            } else {
                cursor.next = p2;
                p2 = p2.next;
            }
            cursor = cursor.next;
        }

        cursor.next = p1 == null ? p2 : p1;
        return dummy.next;
    }
}
