package org.example.algo;

import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;
import org.example.algo.model.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yoyocraft
 * @date 2024/08/21
 */
public class OjAssertUtil {

    public static void judgeResult(Consumer<String> assertion, String fileName) {
        Arrays.stream(readFile(fileName).split(SymbolConstant.NEW_LINE))
                .forEach(assertion);
    }

    public static void assertEquals(String excepted, String actual) {
        if (!excepted.equals(actual)) {
            System.err.printf(OjConstant.ASSERT_TEMPLATE, excepted, actual);
        }
    }

    public static void assertEquals(int excepted, int actual) {
        if (excepted != actual) {
            System.err.printf(OjConstant.ASSERT_TEMPLATE, excepted, actual);
        }
    }

    public static void assertEquals(boolean excepted, boolean actual) {
        if (excepted != actual) {
            System.err.printf(OjConstant.ASSERT_TEMPLATE, excepted, actual);
        }
    }

    private static String readFile(String fileName) {
        ClassLoader classLoader = OjAssertUtil.class.getClassLoader();
        URL url = classLoader.getResource(getFilePath(fileName));
        assert url != null;

        try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
            String line;
            StringBuilder tcsBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // 开头 "#" 为注释，跳过
                if (line.startsWith(SymbolConstant.HASH)) {
                    continue;
                }
                tcsBuilder.append(line).append(SymbolConstant.NEW_LINE);
            }
            return tcsBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFilePath(String fileName) {
        return OjConstant.TEST_CASE_BASE_PATH + fileName;
    }
}
