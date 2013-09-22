package core.DrivingPlan;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This enum type contains the Car Rental Plan Types the member can choose.
 *
 */

public enum PlanType {
    OCCASIONAL("Occasional Driving Plan",50,0,0),
    FREQUENT("Frequent Driving Plan",0,60,Float.parseFloat("0.10")),
    DAILY("Daily Driving Plan",0,100,Float.parseFloat("0.15"));
    
    private PlanType(final String type, final int annualFees, 
            final int monthlyPay, final float discount) {
        this.typeStr = type;
        this.setAnnualFees(annualFees);
        this.setMonthlyPay(monthlyPay);
        this.discount = discount;
    }
    private final String typeStr; 
    private int annualFees, monthlyPay;
    private final float discount;
    
    @Override
    public String toString() {
        return typeStr;
    }
    
    public boolean equals(PlanType type) {
        if(type.equals(type.typeStr))
            return true;
        return false;
    }

    /**
     * @param annualFees the annualFees to set
     */
    public void setAnnualFees(int annualFees) {
        this.annualFees = annualFees;
    }

    /**
     * @return the annualFees
     */
    public int getAnnualFees() {
        return annualFees;
    }

    /**
     * @param monthlyPay the monthlyPay to set
     */
    public void setMonthlyPay(int monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    /**
     * @return the monthlyPay
     */
    public int getMonthlyPay() {
        return monthlyPay;
    }

    /**
     * @return the discount
     */
    public float getDiscount() {
        return discount;
    }
}
