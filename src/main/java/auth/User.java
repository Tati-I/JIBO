package auth;

public class User {
    private String username;
    private String password;
    private String email;
    private String userType;
    private String numPhone;

    public User(String username, String password, String email, String userType, String numPhone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.numPhone = numPhone;
    }

    // دوال getter للوصول إلى معلومات المستخدم
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public String getNumPhone() {
        return numPhone;
    }
}

