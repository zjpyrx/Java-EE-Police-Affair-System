package org.group05.police.DTO;

public class LoginResponse {
    private boolean isSuccess;
    private String message;
    private String token;
    public LoginResponse(boolean isSuccess, String message, String token) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.token = token;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
