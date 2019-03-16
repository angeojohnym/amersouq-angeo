package com.shopping.techxform.amersouq.RetrofitHelpers;

/**
 * Created by techxform on 13-Dec-18.
 */

public class LoginInput {

    private String username;
    private String password;

    public LoginInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
