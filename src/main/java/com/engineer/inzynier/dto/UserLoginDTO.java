package com.engineer.inzynier.dto;

import com.sun.istack.NotNull;

public class UserLoginDTO {
    @NotNull
    private final String username;
    @NotNull
    private final String password;

    public UserLoginDTO(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }
}
