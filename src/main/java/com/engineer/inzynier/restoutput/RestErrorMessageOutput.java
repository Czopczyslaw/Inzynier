package com.engineer.inzynier.restoutput;

public class RestErrorMessageOutput extends Throwable {
    private String errorMessage;

    public RestErrorMessageOutput(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
