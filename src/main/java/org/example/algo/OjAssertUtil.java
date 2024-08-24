package org.example.algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yoyocraft
 * @date 2024/08/21
 */
public class OjAssertUtil {

    private static final String TEST_CASE_PATH = "oj/tc/";

    public static void assertResultWithStream(Consumer<Stream<String>> assertion, String fileName) {
        assertion.accept(Arrays.stream(readFile(fileName).split("\n")));
    }

    public static void assertResult(Consumer<String> assertion, String fileName) {
        Arrays.stream(readFile(fileName).split("\n"))
                .forEach(assertion);
    }

    public static void assertEquals(String excepted, String actual) {
        if (!excepted.equals(actual)) {
            System.err.println("excepted: " + excepted + ", actual: " + actual);
        }
    }

    public static void assertEquals(int excepted, int actual) {
        if (excepted != actual) {
            System.err.println("excepted: " + excepted + ", actual: " + actual);
        }
    }

    /**
     * 解析出 [1,2,3,4,5,6]
     */
    public static int[] parseIntArray(String s) {
        return Arrays.stream(s.substring(1, s.length() - 1).split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * 转换成 [1,2,3,4,5,6]
     */
    public static String parseString(int[] nums) {
        return java.lang.String.format("[%s]", Arrays.stream(nums)
                .mapToObj(java.lang.String::valueOf)
                .collect(Collectors.joining(",")));
    }

    private static String readFile(String fileName) {
        ClassLoader classLoader = OjAssertUtil.class.getClassLoader();
        URL url = classLoader.getResource(getFilePath(fileName));
        assert url != null;

        try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFilePath(String fileName) {
        return TEST_CASE_PATH + fileName;
    }
}
