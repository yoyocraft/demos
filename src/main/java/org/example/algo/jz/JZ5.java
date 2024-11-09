package org.example.algo.jz;

import org.example.algo.OjAssertUtil;
import org.example.algo.TargetType;
import org.example.algo.constant.SymbolConstant;

/**
 * <a href="https://www.nowcoder.com/practice/0e26e5551f2b489b9f58bc83aa4b6c68">JZ5</a>
 * <p>
 * 请实现一个函数，将一个字符串s中的每个空格替换成 "%20" 。
 * 数据范围：0 <= len(s) <= 1000
 */
public class JZ5 {
    public static void main(String[] args) {
        JZ5 jz5 = new JZ5();
        OjAssertUtil.judgeResult((tc) -> {
            String[] inOut = tc.split(SymbolConstant.UNDER_LINE);
            String s = inOut[0];
            String expect = inOut[1];
            String actual = jz5.replaceSpace(s);
            OjAssertUtil.assertEquals(expect, actual);
        }, "jz5", TargetType.JZ);
    }

    public String replaceSpace(String s) {
        return f2(s);
    }

    /**
     * 使用 StringBuilder
     */
    public String f2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 简单模拟
     */
    public String f1(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        char[] ss = s.toCharArray();
        int n = ss.length;
        int spaceCnt = 0;
        for (char c : ss) {
            if (c == ' ') {
                spaceCnt++;
            }
        }
        char[] t = new char[n + spaceCnt * 2];
        int j = t.length - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (ss[i] == ' ') {
                t[j--] = '0';
                t[j--] = '2';
                t[j--] = '%';
            } else {
                t[j--] = ss[i];
            }
        }
        return new String(t);
    }
}
