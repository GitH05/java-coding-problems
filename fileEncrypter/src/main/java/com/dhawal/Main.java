package com.dhawal;

import com.dhawal.views.Welcome;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Welcome w = new Welcome();

        do {
            w.welcomeScreen();
        } while (true);
    }
}