package authorization;

public class Client {
    private String login;
    private String password;
    private String name;
    private ClientType clientType;

    public Client(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        clientType = ClientType.USER;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}