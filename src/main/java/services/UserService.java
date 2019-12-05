package services;

import dao.DaoUserSql;
import models.User;

public class UserService {
    private DaoUserSql daoUserSql;

    public UserService(DaoUserSql daoUserSql) {
        this.daoUserSql = daoUserSql;
    }

    public DaoUserSql getDaoUserSql() {
        return daoUserSql;
    }

    public int getLogin(User user) {
        return daoUserSql.getByNickName(user).getUserId();


    }
    public boolean checkUsers(User check){
        User user = daoUserSql.getByNickName(check);
        return user!=null && user.getPassword().equals(check.getPassword());
    }
}
