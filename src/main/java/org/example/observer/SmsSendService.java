package org.example.observer;

import org.springframework.stereotype.Service;

/**
 * @author yoyocraft
 * @date 2024/08/29
 */
@Service("sms")
public class SmsSendService implements SendMsgService {
    @Override
    public void sendMsg(String content) {
        System.out.println("短信发送：" + content);
    }
}
