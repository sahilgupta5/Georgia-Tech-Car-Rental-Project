package core.User;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This represents the User object model and all it's attributes.
 *
 */

public class User {
    private String username;
    private String password;
    private UserType type;
    
    
    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
        
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param type the type to set
     */
    public void setType(UserType type) {
        this.type = type;
    }
    /**
     * @return the type
     */
    public UserType getType() {
        return type;
    }
}
