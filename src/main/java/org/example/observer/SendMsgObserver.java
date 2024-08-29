package org.example.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送消息的观察者，
 *
 * @author yoyocraft
 * @date 2024/08/29
 */
@Component
public class SendMsgObserver implements Observer {

    @Autowired
    private List<SendMsgService> sendMsgServices;

    @Override
    public void notify(String bizType, String content) {
        List<String> sendMsgStrategyList = ConfigCenter.getSendMsgStrategy(bizType);
        sendMsgServices.forEach(sendMsgService -> {
            // 配置的策略在内，发送
            if (sendMsgStrategyList.contains(sendMsgService.getClass().getDeclaredAnnotation(Service.class).value())) {
                sendMsgService.sendMsg(content);
            }
        });
    }
}
