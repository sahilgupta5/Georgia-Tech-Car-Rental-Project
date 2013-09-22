package core.CreditCard;

import java.math.BigInteger;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the Credit Card Model Object which contains all the attributes of the car.
 *
 */

public class CreditCard {
    private Integer cvv;
    private BigInteger cardNumber;
    private String nameOnCard, billingAddress;
    private String expiryDate;
    
    
    public CreditCard(BigInteger cardNumber, String nameOnCard, int cvv, String expiryDate,
            String billingAddress){
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.billingAddress = billingAddress;
    }
    
    public CreditCard() {
        this(null, null, 0, null, null);
    }

    /**
     * @return the cardNumber
     */
    public BigInteger getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the nameOnCard
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * @param nameOnCard the nameOnCard to set
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    /**
     * @return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the billingAddress
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billingAddress to set
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
