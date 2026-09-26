package com.dhawal.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import com.dhawal.dao.UserDao;
import com.dhawal.modal.User;
import com.dhawal.service.GenerateOtp;
import com.dhawal.service.SendOTPService;
import com.dhawal.service.UserService;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcom To The File Encrypter:");
        System.out.println("Press 1 To Login");
        System.out.println("Press 2 To Signup");
        System.out.println("Press 0 To Exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        switch (choice) {
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter email:");
        String email = sc.nextLine();

        try {
            if (UserDao.isExist(email)) {
                String genOtp = GenerateOtp.getOtp();

                SendOTPService.sendOTP(email, genOtp);

                System.out.println("\nEnter OTP:");
                String otp = sc.nextLine();

                if (otp.equals(genOtp)) {
                    UserView uv = new UserView(email);
                    uv.home();
                    System.out.println("\nSuccessfully Verified!");
                } else {
                    System.out.println("\nInvalid OTP!");
                }
            } else {
                System.out.println("\nUser not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter name:");
        String name = sc.nextLine();
        System.out.println("Enter email:");
        String email = sc.nextLine();

        String genOtp = GenerateOtp.getOtp();
        SendOTPService.sendOTP(email, genOtp);
        System.out.println("\nEnter OTP:");
        String otp = sc.nextLine();

        if (otp.equals(genOtp)) {
            User user = new User(name, email);

            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("\nUser Registered!");
                case 1 -> System.out.println("\nuser already Exist!");
            }

        } else {
            System.out.println("\nInvalid OTP!");
        }

    }
}
