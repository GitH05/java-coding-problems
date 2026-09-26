package com.dhawal.service;

import java.sql.SQLException;

import com.dhawal.dao.UserDao;
import com.dhawal.modal.User;

public class UserService {
    public static Integer saveUser(User user) {
        try {
            if(UserDao.isExist(user.getEmail())) {
                return 0;
            }
            else {
                return UserDao.saveUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
