package com.engineer.inzynier.restoutput;

import com.sun.istack.NotNull;

public class RestErrorMessageOutput extends Throwable {
    @NotNull
    private final String errorMessage;

    public RestErrorMessageOutput(@NotNull String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @NotNull
    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
