package spdu2022.java.project.beutysalon.security.controllers;

public class AuthDTO {
    private String login;
    private String password;

    public AuthDTO() {
    }

    public AuthDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
