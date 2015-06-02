package com.example.android.cfmaterial;

public class User {

    private static User instance;

    private User() {
        // Get stuff from shared preferences
    }

    public User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }



}
