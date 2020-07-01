package com.example.mochon;

import android.app.Application;

public class Global extends Application {
    static  String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
