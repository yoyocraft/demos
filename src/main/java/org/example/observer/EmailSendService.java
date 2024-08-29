package org.example.observer;

import org.springframework.stereotype.Service;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
@Service("email")
public class EmailSendService implements SendMsgService {
    @Override
    public void sendMsg(String content) {
        System.out.println("邮件发送：" + content);
    }
}
