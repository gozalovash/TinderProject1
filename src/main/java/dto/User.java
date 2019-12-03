package dto;

public class User {
    private int userId;
    private String userName;
    private String userSurname;
    private String password;
    private String photoUrl;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public User() {
    }

    public User(int userId, String userName, String userSurname, String password, String photoUrl) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.password = password;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "User{ Id: " + userId
                + " Name: " + userSurname
                + " Surname: " + userSurname
                + " Password: " + password
                + " Photo Url: " + photoUrl
                +"}";

    }
}
