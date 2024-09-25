package org.example.login.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import org.example.login.constants.UserConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @SaCheckLogin
    @RequestMapping("/get")
    public String get() {
        return "这是受保护资源";
    }

    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @RequestMapping("/get2")
    public String get2() {
        return "这是受保护资源2";
    }
}
