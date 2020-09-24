package com.cricket.packages.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {

    private String userId;
    @NotNull
    @Size(min =  8,max =8,message = "Temporary Password must have length 8")
    private String token;
    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9]{8,14}",message="length must be between 8 and 14")
    @Size(min = 8, max = 14)
    private String newPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
