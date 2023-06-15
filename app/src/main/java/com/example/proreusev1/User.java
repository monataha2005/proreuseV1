package com.example.proreusev1;

public class User {
    private String userName;
    private String password;
    private String email;
    private String location;

    private User(){

    }
    public User(String userName, String password, String email, String location) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
