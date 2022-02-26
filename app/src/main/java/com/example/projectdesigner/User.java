package com.example.projectdesigner;
import com.google.gson.annotations.SerializedName;
public class User {

    public String email;
    public String password;
    public String confirmPassword;
    @SerializedName("success")
    private String success;

    public User(String email, String password, String confirmPassword, String success) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.success = success;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getSuccess() {
        return success;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", success='" + success + '\'' +
                '}';
    }
}
