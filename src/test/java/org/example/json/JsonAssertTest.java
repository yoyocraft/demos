package org.example.json;

import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;


@RunWith(JUnit4.class)
public class JsonAssertTest {

    String oldJson = ResourceUtil.readUtf8Str("json/old.json");
    String newJson = ResourceUtil.readUtf8Str("json/new.json");

    @Test
    public void test_strict() {
        // 不可扩展、顺序必须一致
        JSONAssert.assertEquals(oldJson, newJson, JSONCompareMode.STRICT);
    }

    @Test
    public void test_lenient() {
        // 可扩展、顺序可以不一致
        JSONAssert.assertEquals(oldJson, newJson, JSONCompareMode.LENIENT);
    }

    @Test
    public void test_nonExtensible() {
        // 不可扩展，顺序可以不一致
        JSONAssert.assertEquals(oldJson, newJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void test_strictOrder() {
        // 可扩展、顺序必须一致
        JSONAssert.assertEquals(oldJson, newJson, JSONCompareMode.STRICT_ORDER);
    }

}
