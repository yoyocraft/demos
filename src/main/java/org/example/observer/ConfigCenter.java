package org.example.observer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 模拟配置中心
 *
 * @author yoyocraft
 * @date 2024/08/29
 */
public class ConfigCenter {

    private static final Map<String /* bizType */, String /* send msg type*/> SEND_MSG_CONFIG = Maps.newHashMap();

    static {
        SEND_MSG_CONFIG.put("bizType1", "sms,email");
        SEND_MSG_CONFIG.put("bizType2", "sms");
    }

    public static List<String> getSendMsgStrategy(String bizType) {
        return Lists.newArrayList(SEND_MSG_CONFIG.get(bizType).split(","));
    }
}
