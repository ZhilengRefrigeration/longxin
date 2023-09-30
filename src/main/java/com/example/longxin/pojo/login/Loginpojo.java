package com.example.longxin.pojo.login;

import lombok.Data;

/**
 * @author 制冷
 * @date 2023/6/16 16:21
 * @description Loginpojo
 */
@Data
public class Loginpojo {
    private String username;
    private String password;

    public Loginpojo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
