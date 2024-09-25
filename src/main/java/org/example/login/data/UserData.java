package org.example.login.data;

import org.example.login.constants.UserConstant;
import org.example.login.model.User;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
public class UserData {
    public static final User ADMIN_USER = new User();
    public static final User USER = new User();

    static {
        ADMIN_USER.setId(1L);
        ADMIN_USER.setUsername("yoyocraft");
        ADMIN_USER.setPassword("123456");
        ADMIN_USER.setRole(UserConstant.ADMIN_ROLE);

        USER.setId(2L);
        USER.setUsername("user");
        USER.setPassword("123456");
        USER.setRole(UserConstant.USER_ROLE);
    }

}
