package services;

import dao.DaoUserSql;
import models.User;



public class LoginService {
    private DaoUserSql users;

    public void checkExistence(String user_name, String user_password) throws Exception {
        for(User user:users){
            if(user_name.equals(user.getNickName()) && user_password.equals(user_password)){
                return;
            }
        }
        throw new Exception("Such account does not exist. Check your username and password and try again");
    }
}
