package org.example.algo;

/**
 * @author yoyocraft
 * @date 2024/09/03
 */
public enum TargetType {

    /**
     * leetcode
     */
    LC,

    /**
     * video platform like bilibili
     */
    VIDEO,

    /**
     * for test
     */
    TEST,
    ;

    public static String getTargetName(TargetType targetType) {
        return targetType.name().toLowerCase();
    }
}
