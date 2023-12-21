package courier;

import java.security.Principal;

public class CourierCred implements org.apache.http.auth.Credentials {
    private String login;
    private String password;

    public CourierCred(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCred from(Courier courier) {
        return new CourierCred(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}