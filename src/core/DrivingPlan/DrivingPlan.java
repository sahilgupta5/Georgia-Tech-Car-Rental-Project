package core.DrivingPlan;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the driving plan object model.
 *
 */

public class DrivingPlan {
    String name;
    Integer annualFees, monthlyPay;
    Float discount;
        
    public DrivingPlan(String name, int annualFees, int monthlyPay,
            float discount) {
        this.name = name;
        this.annualFees = annualFees;
        this.monthlyPay = monthlyPay;
        this.discount = discount;
    }
    
    public DrivingPlan(PlanType type) {
        this(type.toString(), type.getAnnualFees(), type.getMonthlyPay(), type.getDiscount());
    }
    
    public DrivingPlan() {
        this(null, 0, 0, 0);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the annualFees
     */
    public Integer getAnnualFees() {
        return annualFees;
    }

    /**
     * @param annualFees the annualFees to set
     */
    public void setAnnualFees(Integer annualFees) {
        this.annualFees = annualFees;
    }

    /**
     * @return the monthlyPay
     */
    public Integer getMonthlyPay() {
        return monthlyPay;
    }

    /**
     * @param monthlyPay the monthlyPay to set
     */
    public void setMonthlyPay(Integer monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    /**
     * @return the discount
     */
    public Float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
