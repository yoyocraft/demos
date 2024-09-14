package org.example.algo.game2048;

/**
 * @author yoyocraft
 * @date 2024/09/14
 */
public enum DirectionEnum {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static DirectionEnum resolve(String direction) {
        return DirectionEnum.valueOf(direction.toUpperCase());
    }

}
