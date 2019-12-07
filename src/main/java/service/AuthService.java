package service;

import java.util.HashMap;
import java.util.Map;

public class AuthService implements Auth{
    Map<String, String> Users=new HashMap<>();
    public AuthService(){ Users.put("user", "123");
    }

    @Override
    public boolean check(String username, String password) {
        return false;
    }
}
//could be deleted because of filters