package org.group05.police.DTO;

public class RegisterResponse {

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private boolean isSuccess;
    private String message;

    public RegisterResponse(boolean success, String message) {
        this.isSuccess = success;
        this.message = message;
    }

}
