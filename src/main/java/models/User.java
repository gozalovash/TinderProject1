package models;

public class User {
    private int userId;
    private String nickName;
    private String userName;
    private String userSurname;
    private String password;
    private String photoUrl;

    public User(String password) {
        this.password=password;
    }



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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public User(String nickName, String userName, String surname, String password, String photoUrl) {
        this.nickName = nickName;
        this.userName = userName;
        this.userSurname = surname;
        this.password = password;
        this.photoUrl = photoUrl;
    }

    public User(int userId, String nickName, String userName, String userSurname, String password, String photoUrl) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.password = password;
        this.photoUrl = photoUrl;
    }

    public User(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public User(int userId, String nickName, String userName, String surname, String imgUrl) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.userSurname = surname;
        this.photoUrl = imgUrl;
    }


    @Override
    public String toString() {
        return "User{ Id: " + userId
                + " Nickname :" + nickName
                + " Name: " + userSurname
                + " Surname: " + userSurname
                + " Password: " + password
                + " Photo Url: " + photoUrl
                + "}";

    }
}
