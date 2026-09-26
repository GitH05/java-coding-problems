package com.dhawal.views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dhawal.dao.DataDao;
import com.dhawal.modal.Data;

public class UserView {

    Scanner sc = new Scanner(System.in);

    private String email;

    public UserView(String email) {
        this.email = email;
    }

    public void home() {
        do {
            System.out.println("\n Welcome " + this.email);
            System.out.println("\nPres 1 to show hidden files:");
            System.out.println("Press 2 to hide a file:");
            System.out.println("Press 3 to unhide a file:");
            System.out.println("Press 0 to Exit");

            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = DataDao.getAllFiles(email);
                        System.out.println("Id  -       File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " -       " + file.getFilenmae());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(), path, this.email);
                    try {
                        DataDao.hideFile(file);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    try {
                        List<Data> files = DataDao.getAllFiles(email);
                        System.out.println("Id  -  File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFilenmae());
                        }
                        System.out.println("Enter the `id` of file to unhide:");
                        int id = Integer.parseInt(sc.nextLine());

                        boolean isValidId = false;

                        for (Data file : files) {
                            if (file.getId() == id) {
                                isValidId = true;
                                break;
                            }
                        }
                        if (isValidId) {
                            DataDao.unhide(id);
                        } else {
                            System.out.println("\nInvalid id!\n");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 0 -> {
                    System.exit(0);
                }
            }

        } while (true);
    }
}