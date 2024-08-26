package org.example.algo;

import org.example.algo.constant.OjConstant;
import org.example.algo.constant.SymbolConstant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yoyocraft
 * @date 2024/08/21
 */
public class OjAssertUtil {

    public static void judgeResultWithStream(Consumer<Stream<String>> assertion, String fileName) {
        assertion.accept(Arrays.stream(readFile(fileName).split(SymbolConstant.NEW_LINE)));
    }

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

    /**
     * @param s [item, item, ...]
     * @return int[]
     */
    public static int[] parseIntArray(String s) {
        return Arrays.stream(s.substring(1, s.length() - 1).split(SymbolConstant.COMMA))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * @param nums array
     * @return [item, item, ...]
     */
    public static String parseString(int[] nums) {
        return java.lang.String.format(OjConstant.ARRAY_TEMPLATE, Arrays.stream(nums)
                .mapToObj(java.lang.String::valueOf)
                .collect(Collectors.joining(SymbolConstant.COMMA)));
    }

    /**
     * @param list list
     * @return [...], [[...],[...],...], ...
     */
    public static String parseString(List<?> list) {
        if (list.isEmpty()) {
            return OjConstant.EMPTY_LIST;
        }

        return list.stream()
                .map(element -> {
                    if (element instanceof List) {
                        return parseString((List<?>) element);
                    } else {
                        return String.valueOf(element);
                    }
                })
                .collect(Collectors.joining(SymbolConstant.COMMA, SymbolConstant.LEFT_BRACKET, SymbolConstant.RIGHT_BRACKET));
    }


    private static String readFile(String fileName) {
        ClassLoader classLoader = OjAssertUtil.class.getClassLoader();
        URL url = classLoader.getResource(getFilePath(fileName));
        assert url != null;

        try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
            String line;
            StringBuilder tcsBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
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
