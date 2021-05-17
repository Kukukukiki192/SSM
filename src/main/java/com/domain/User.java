package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录账户 实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    private String username;
    private String password;
}
