package com.shopping.techxform.amersouq.RetrofitHelpers;

/**
 * Created by techxform on 15-Dec-18.
 */

public class SignUpInput {

    private String first_name;
    private String last_name;
    private String contact_number;
    private String password;
    private String email;
    private String username;
    private String device_type;
    private String device_token;

    public SignUpInput(String first_name, String last_name, String contact_number, String password, String email, String username, String device_type, String device_token) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.password = password;
        this.email = email;
        this.username = username;
        this.device_type = device_type;
        this.device_token = device_token;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}


