package org.example.login.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.example.login.constants.UserConstant;
import org.example.login.data.UserData;
import org.example.login.model.User;
import org.example.login.utils.DeviceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/doLogin")
    public String doLogin(String username, String password, HttpServletRequest request) {
        User user = null;
        if ("yoyocraft".equals(username) && "123456".equals(password)) {
            user = UserData.ADMIN_USER;
        } else if ("user".equals(username) && "123456".equals(password)) {
            user = UserData.USER;
        } else {
            return "用户名或密码错误";
        }

        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE_KEY, user);
        StpUtil.login(user.getId(), DeviceUtils.getRequestDevice(request));
        StpUtil.getSession().set(UserConstant.USER_LOGIN_STATE_KEY, user);
        return "登陆成功";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("/loginUser")
    public User loginUser() {
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (Objects.isNull(loginId)) {
            return null;
        }
        return (User) StpUtil.getSession().get(UserConstant.USER_LOGIN_STATE_KEY);
    }

    @SaCheckLogin
    @RequestMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "退出成功";
    }

}
