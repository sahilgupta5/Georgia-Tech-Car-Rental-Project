package core.User;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This enum type contains the different kinds of users who use this application.
 *
 */

public enum UserType {
    MEMBER("Georgia Tech Students/Faculty"),
    EMPLOYEE("GTCR Employee"),
    ADMIN("Administrator");
    
    private UserType(final String s) {
        this.s = s;
    }
    private final String s;
    
    @Override
    public String toString() {
        return s;
    }
    
    public boolean equals(UserType type) {
        if(s.equals(type.s))
            return true;
        return false;
    }
}
