package cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookiesService {
    private String cookieName = "onlineUser";
    private HttpServletResponse response;
    HttpServletRequest request;

    public CookiesService(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
    }

    public Cookie getCookies() {
        Cookie cookie = null;
        Cookie cookies[] = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    cookie = c;
                }
            }

        }
        return cookie;
    }

    public void saveCookies(int value) {
        response.addCookie(new Cookie(cookieName, String.valueOf(value)));
    }


    public void deleteCookies() {
        Arrays.stream(request.getCookies()).
                filter(c -> c.getName().equalsIgnoreCase(cookieName)).
                map(c -> new Cookie(c.getName(), c.getValue()) {{
                    setMaxAge(0);
                }}).forEach(response::addCookie);
    }

}
