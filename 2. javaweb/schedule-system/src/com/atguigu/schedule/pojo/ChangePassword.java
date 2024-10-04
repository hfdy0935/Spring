package com.atguigu.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePassword {
    private Integer uid;
    private String username;
    private String oldPassword;
    private String newPassword;

    public ChangePassword(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
