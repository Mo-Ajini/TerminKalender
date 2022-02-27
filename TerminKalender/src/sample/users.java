package sample;

/**
 * users Klasse um ein Account anzulegen
 */

public class users {

    /**
     * Variable deklarieren
     */
    int id ;
    String username, password, email , type;

    /**
     * Setter Methoden
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter Methoden
     * @return
     */

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    /**
     * Konstruktor um ein User zu erstellen
     * @param id
     * @param username
     * @param password
     * @param email
     * @param type
     */
    public users(int id, String username, String password, String email, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }
}