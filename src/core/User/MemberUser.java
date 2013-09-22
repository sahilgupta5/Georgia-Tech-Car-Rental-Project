package core.User;

import core.CreditCard.CreditCard;
import core.DrivingPlan.DrivingPlan;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the Member user for the application.
 *
 */

public class MemberUser extends User{
    String username, firstName=null, lastName=null, email=null, phone=null, address=null, middleInit=null;
    UserType type;
    CreditCard creditCard=null;
    DrivingPlan drivingPlan=null;
    
    public MemberUser(String username, String password) {
        super(username, password, UserType.MEMBER);
        this.username = username;
        this.type = UserType.MEMBER;
    }

    public void setAllMemFields(String firstName, String middleInit,
            String lastName, String email, String phone, String address,
            CreditCard creditCard, DrivingPlan drivingPlan) {
        this.firstName = firstName;
        this.middleInit = middleInit;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.creditCard = creditCard;
        this.drivingPlan= drivingPlan;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the middleInit
     */
    public String getMiddleInit() {
        return middleInit;
    }

    /**
     * @param middleInit the middleInit to set
     */
    public void setMiddleInit(String middleInit) {
        this.middleInit = middleInit;
    }

    /**
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * @return the drivingPlan
     */
    public DrivingPlan getDrivingPlan() {
        return drivingPlan;
    }

    /**
     * @param drivingPlan the drivingPlan to set
     */
    public void setDrivingPlan(DrivingPlan drivingPlan) {
        this.drivingPlan = drivingPlan;
    }
}
