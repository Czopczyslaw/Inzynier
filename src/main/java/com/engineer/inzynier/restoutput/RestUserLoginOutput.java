package com.engineer.inzynier.restoutput;

import com.sun.istack.NotNull;

public class RestUserLoginOutput {
    @NotNull
    private final String username;
    @NotNull
    private final String uid;

    public RestUserLoginOutput(@NotNull String username, @NotNull String uid) {
        this.username = username;
        this.uid = uid;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    @NotNull
    public final String getUid() {
        return this.uid;
    }
}
