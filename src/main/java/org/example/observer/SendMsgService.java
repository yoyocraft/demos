package org.example.observer;

/**
 * 发送消息的能力抽象，策略接口
 *
 * @author yoyocraft
 * @date 2024/08/29
 */
public interface SendMsgService {

    /**
     * 发送消息
     * @param content 消息内容
     */
    void sendMsg(String content);
}
