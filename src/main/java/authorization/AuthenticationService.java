package authorization;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationService {
    private static Set<Client> clients = new HashSet<>();
    private String sqlLogin = "login";
    private String sqlPass = "password";
    private String connectionURL = "jdbc:mysql://some_server_address_tbd";

    public AuthenticationService() { // constructor updating set on initialization
        GetClientsFromMySQL();

    }
    public void GetClientsFromMySQL() {
        GetClientsFromBase("SELECT * FROM person"); //update list of all sql to local set

    }

    public void GetClientsFromBase(String sqlRequest) { //update set by specific sqlRequest
        Client client;
        String connectionURL = "jdbc:mysql://some_server_address_tbd";
        try (Connection conn = DriverManager.getConnection(connectionURL, "username", "password");
             PreparedStatement ps = conn.prepareStatement(sqlRequest);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                clients.add(new  Client(login, password, name));
            }
        } catch (SQLException e) {
        }
    }

    public Client findLoginAndPassword(String login, String password) { // return client with same login and password from local set
        for(Client c : clients) {
            if(c.getLogin().equals(login) && c.getPassword().equals(password)) {
                return c;
            }
        } return null;
    }

    public boolean AccessGranted(Client client) { // return true if client is in local set (by login and password)
        return (findLoginAndPassword(client.getLogin(), client.getPassword()) != null);
    }

    public ClientType checkStatus(Client client) { // return type of Client (User, Admin etc...)
        return client.getClientType();
    }

    public void ChangeCLientType(Client client, ClientType clientType) { // change type of Client
        client.setClientType(clientType);
    }
}
