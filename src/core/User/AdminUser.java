package core.User;

/**
 * 
 * @author Rochelle Lobo
 * This class represents the Admin User object model.
 *
 */

public class AdminUser extends User {
    String username;
    UserType type;

    public AdminUser(String username, String password) {
        super(username, password, UserType.ADMIN);
        this.username = username;
        this.type = UserType.MEMBER;
    }

}
