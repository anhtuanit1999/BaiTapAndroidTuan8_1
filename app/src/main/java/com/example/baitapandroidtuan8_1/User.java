package com.example.baitapandroidtuan8_1;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private String password;
    private int normal;
    private int happy;
    private int angry;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.normal = 0;
        this.happy = 0;
        this.angry = 0;
    }
}
