package org.example.login.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String role;

}
