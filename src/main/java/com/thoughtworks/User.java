package com.thoughtworks;

public class User {
    private String name;
    private String phone;
    private String email;
    private String passwords;
    private int tries = 0;

    public User(String name, String phone, String email, String passwords) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.passwords = passwords;
    }

    public User(String name, String phone, String email, String passwords, int tries) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.passwords = passwords;
        this.tries = tries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
}
