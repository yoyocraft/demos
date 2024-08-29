package org.example.observer;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
public interface Observer {

    /**
     * 通知
     *
     * @param bizType 业务类型
     * @param content 通知内容
     */
    void notify(String bizType, String content);
}
