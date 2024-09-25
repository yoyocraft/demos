package org.example.login.support;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import org.example.login.constants.UserConstant;
import org.example.login.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        User user = (User) session.get(UserConstant.USER_LOGIN_STATE_KEY);
        return Lists.newArrayList(
                user.getRole()
        );
    }
}
