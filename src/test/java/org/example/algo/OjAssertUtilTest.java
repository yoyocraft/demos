package org.example.algo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/08/26
 */
public class OjAssertUtilTest {

    @Test
    public void test_parserString_List() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        String result = OjAssertUtil.parseString(list);
        System.out.println(result);

        List<List<Integer>> listList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        System.out.println(OjAssertUtil.parseString(listList));
    }
}
