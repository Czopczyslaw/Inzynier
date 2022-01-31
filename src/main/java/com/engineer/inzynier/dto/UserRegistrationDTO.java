package com.engineer.inzynier.dto;

import com.sun.istack.NotNull;

public class UserRegistrationDTO {
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    @NotNull
    private final String email;

    public UserRegistrationDTO(@NotNull String username, @NotNull String password, @NotNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserRegistrationDTO() {
        this(null, null, null);
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }
}
