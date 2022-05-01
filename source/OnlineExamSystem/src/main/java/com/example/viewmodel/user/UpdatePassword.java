package com.example.viewmodel.user;

import lombok.Data;

@Data
public class UpdatePassword {
    private String oldPassword;
    private String newPassword;

    public UpdatePassword() {
    }

    public UpdatePassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
