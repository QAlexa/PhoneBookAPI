package models;

public class AuthenticationRequestModel {

    private String username;
    private String password;

    public static  AuthenticationRequestModel username (String username){

        return new AuthenticationRequestModel(username);
    }

    public static AuthenticationRequestModel password (String password){

        return new AuthenticationRequestModel(password);
    }
    public AuthenticationRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequestModel(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
