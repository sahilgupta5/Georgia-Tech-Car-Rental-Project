package core.User;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This represents the Employee User Object.
 *
 */

public class EmployeeUser extends User {
    String username;
    UserType type;

    public EmployeeUser(String username, String password) {
        super(username, password, UserType.EMPLOYEE);
        this.username = username;
        this.type = UserType.MEMBER;
    }

}
