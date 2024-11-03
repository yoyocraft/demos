package org.example.algo.jz;

import org.example.algo.ModelParser;
import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * <a href="https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035">JZ1</a>
 * <p>
 * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 * 0 <= 链表长度 <= 10000
 */
public class JZ1 {

    public static void main(String[] args) {
        JZ1 jz1 = new JZ1();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.SPACE);
            ListNode listNode = ModelParser.buildList(inOut[0]);
            String expect = inOut[1];
            ArrayList<Integer> actual = jz1.printListFromTailToHead(listNode);
            OjAssertUtil.assertEquals(expect, ModelParser.parseString(actual));
        }, "jz1", TargetType.JZ);
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        return f1(listNode);
    }

    /**
     * 直接遍历
     */
    private static ArrayList<Integer> f3(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (listNode == null) {
            return ans;
        }

        ListNode cursor = listNode;
        while (cursor != null) {
            ans.add(cursor.val);
            cursor = cursor.next;
        }

        Collections.reverse(ans);
        return ans;
    }

    /**
     * 栈方式
     */
    public static ArrayList<Integer> f2(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (listNode == null) {
            return ans;
        }

        Deque<Integer> stk = new ArrayDeque<>();
        ListNode cursor = listNode;
        while (cursor != null) {
            stk.push(cursor.val);
            cursor = cursor.next;
        }

        while (!stk.isEmpty()) {
            ans.add(stk.pop());
        }
        return ans;
    }

    /**
     * 递归方式
     */
    public ArrayList<Integer> f1(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (listNode == null) {
            return ans;
        }

        recur(listNode, ans);
        return ans;
    }

    void recur(ListNode listNode, ArrayList<Integer> ans) {
        if (listNode == null) {
            return;
        }

        recur(listNode.next, ans);
        ans.add(listNode.val);
    }
}
