package com.demo.service;

import com.demo.dao.LoginDao;

public class UserService {
    public boolean userLogin(String username,String password){
        return LoginDao.verfyLogin(username,password);
    }
}
