package services;

import dao.Dao;
import dao.DaoUserSql;
import models.User;



public class LoginService {
    private DaoUserSql users;
    public LoginService(){
        users=new DaoUserSql();
    }

    public void checkExistence(String user_name, String user_password) throws Exception {

        throw new Exception("Such account does not exist. Check your username and password and try again");
    }
}
