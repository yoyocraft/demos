package org.example.geek;

/**
 * @author yoyocraft
 * @date 2024/08/10
 */
public class IdGenerationFailureException extends RuntimeException {

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
