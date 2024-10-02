package org.example.algo.json;

import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author yoyocraft
 * @date 2024/10/01
 */
@RunWith(JUnit4.class)
public class JsonAssertTest {

    @Test
    public void test_jsonAssert() {
        String json1 = ResourceUtil.readUtf8Str("json/part1.json");
        String json2 = ResourceUtil.readUtf8Str("json/part2.json");
        JSONAssert.assertEquals(json1, json2, JSONCompareMode.LENIENT);
    }

}
