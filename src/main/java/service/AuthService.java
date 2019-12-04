package service;

import java.util.HashMap;
import java.util.Map;

public class AuthService implements Auth{
    Map<String, String> users=new HashMap<>();
    public AuthService(){
        users.put("user", "123");
    }

    @Override
    public boolean check(String username, String password) {
        return false;
    }
}
