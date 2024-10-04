package com.atguigu.schedule.pojo;

import java.io.Serializable;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String password;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
